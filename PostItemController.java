package Controller;

import Model.PageLoader;
import Mutual.Post;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class PostItemController {

    public Label title;
    public Label username;
    public AnchorPane root;
    public ImageView profile;
    public Button repostButton;
    Post post;

    public PostItemController(Post post) throws IOException {
        new PageLoader().load("postItem" , this);
        this.post = post;
    }
    //this anchor pane is returned to be set as the list view item
    public AnchorPane init() {
        username.setText(post.getWriter());
        title.setText(post.getTitle());
        return root;
    }

    /*public void repost(ActionEvent actionEvent) {
        System.out.println("repost");
    }*/
}
