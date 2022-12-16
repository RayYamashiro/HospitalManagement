import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.example.App;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.util.ResourceBundle;
import java.util.concurrent.TimeoutException;

public class TestFX extends ApplicationTest {
    Stage primaryStage;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    static ResourceBundle bundle;
    @Before
    public void setUpClass() throws Exception {
        ApplicationTest.launch(App.class);
    }

    @BeforeClass
    public static void setupHeadLessMode()
    {
        if(Boolean.getBoolean("headless"))
        {
            System.setProperty("testfx.robot", "glass");
            System.setProperty("testfx.headless", "true");
            System.setProperty("prism.order", "sw");
            System.setProperty("prism.text", "t2k");
            System.setProperty("java.awt.headless", "true");
        }
        bundle = ResourceBundle.getBundle("Bundle");
    }
    @After
    public void afterTest() throws TimeoutException {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Override
    public void start(Stage stage)
    {
        stage.show();
    }

    public <T extends Node> T find(final  String query)
    {
        return (T) lookup(query).queryAll().iterator().next();
    }
}


