package Controller;

import Model.CommunicateWithServer;
import Model.PageLoader;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import Mutual.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TimeLineController {

    //elements
    public ListView listOfPosts;
    public TextArea descriptionText;
    public TextField titleText;
    public Button publishButton;
    public Button menu;
    public Button refresh;
    public Button allUsersButton;
    public Button likeButton;
    public Button commentButton;
    public Button repostButton;

    //other properties
    ArrayList<Post> posts = new ArrayList<>();
    Post currentPost = new Post();
    static String currentUsername;

    //methods

    public static void setCurrentUsername(String currentUsername) {
        TimeLineController.currentUsername = currentUsername;
    }

    @FXML
    public void initialize() {
        HashMap<String,Object> map = new HashMap<>();
        HashMap<String,Object> ansMap = new HashMap<>();
        map.put("command" , "getTimeLinePosts");
        map.put("currentUser" , currentUsername);
        ansMap = CommunicateWithServer.communicateWithServer(map);
        posts = (ArrayList<Post>) ansMap.get("timeLinePosts");
        //show the post array in list view
        listOfPosts.setItems(FXCollections.observableArrayList(posts));
        //customize each cell of postList with new graphic object PostItem
        listOfPosts.setCellFactory(listOfPosts -> new PostItem());
    }

    public void publishPost(ActionEvent actionEvent) {
        HashMap<String,Object> map = new HashMap<>();
        HashMap<String,Object> ansMap = new HashMap<>();
        //set the post features
        currentPost.setTitle(titleText.getText());
        currentPost.setDescription(descriptionText.getText());
        currentPost.setWriter(currentUsername);
        currentPost.setPublisher(currentUsername);
        //save the post in arraylist
        posts.add(currentPost);
        //show the arraylist in listview
        listOfPosts.setItems(FXCollections.observableArrayList(posts));
        listOfPosts.setCellFactory(listOfPosts -> new PostItem());
        //empty fields
        titleText.setText("");
        descriptionText.setText("");
        map.put("command","addPost");
        map.put("username",currentUsername);
        map.put("post",currentPost);
        CommunicateWithServer.communicateWithServer(map);
    }

    public void clear(ActionEvent actionEvent) {
        titleText.setText("");
        descriptionText.setText("");
    }

    public void openMenu(ActionEvent actionEvent) {
        try {
            new PageLoader().load("menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refresh(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("timeLine");
    }

    public void showPost(MouseEvent mouseEvent) {
        /*HashMap<String,Object> map = new HashMap<>();
        HashMap<String,Object> ansMap = new HashMap<>();
        likeButton.setVisible(true);
        commentButton.setVisible(true);
        repostButton.setVisible(true);
        String postTitle = listOfPosts.getSelectionModel().getSelectedItems().toString();
        postTitle = postTitle.substring(1,postTitle.length()-1);
        map.put("command" , "findPostByTitle");
        map.put("title" , postTitle);
        ansMap = CommunicateWithServer.communicateWithServer(map);*/


    }

    public void listAllUsers(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("usersList");
    }

    public void like(ActionEvent actionEvent) {
    }

    public void comment(ActionEvent actionEvent) {
    }

    public void repost(ActionEvent actionEvent) {
    }
}
