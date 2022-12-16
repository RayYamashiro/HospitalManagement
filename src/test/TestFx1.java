import javafx.scene.Node;
import org.junit.Test;
import org.springframework.boot.ResourceBanner;
import org.springframework.boot.web.reactive.server.ReactiveWebServerFactory;
import org.testfx.api.FxRobotException;

import java.awt.*;
import java.util.ResourceBundle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestFx1 extends TestFX {
    final String Login_TextArea_ID = "#login_field";
    final String Pass_TextArea_ID = "#password_field";
    final String Login_Button_ID = "#authSignInButton";

    @Test(expected = FxRobotException.class)
    public void clickWrong()
    {
        clickOn("#sector4");
    }

    @Test
    public void auth()
    {
        String mainAppTitle = ResourceBundle.getBundle("Bundle").getString("main.app.title");
        clickOn(Login_TextArea_ID).write("admin");
        clickOn(Pass_TextArea_ID).write("admin");
        clickOn(Login_Button_ID);

        try {
            wait(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        assertTrue("Correct" , getPrimaryStage().isShowing());
    }

    @Test
    public void incorrectAuth()
    {
        Node login = find("#login");
        Label label = new Label(login.toString());
        clickOn(Login_TextArea_ID).eraseText(10);
        clickOn(Pass_TextArea_ID).eraseText(10);
        clickOn(Login_TextArea_ID).write("ddd");
        clickOn(Pass_TextArea_ID).write("admdddin");
        clickOn(Login_Button_ID);
        assertEquals("Вы ввели неверный логин или пароль" , label.getText());
    }

    @Test
    public void reg()
    {

    }
}
