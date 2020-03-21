import static org.lwjgl.util.glu.GLU.gluPerspective;

import java.io.IOException;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Main {
	Blocks blocks = new Blocks();
	Controller controller = new Controller();
	TextureLoader textureLoader = new TextureLoader();
	
	public Main() {
		try {
			Display.setDisplayModeAndFullscreen(Display.getDesktopDisplayMode());
			//Display.setDisplayMode(new DisplayMode(1000, 600));
			Display.setTitle("Gordey LWJGL Test");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(60, (float) 1440 / (float) 900, 0.001f, 1000);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_DEPTH_TEST);
		
		
		int tex = 0;
		try {
			tex = TextureLoader.loadTexture("resources/textures.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Mouse.setGrabbed(true);
		
		while(!Display.isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
			glClearColor(0.0f, 0.7f, 0.9f, 1.0f);
			
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
				Display.destroy();
				System.exit(0);
			}
			
			//Rendering
			blocks.Rendering();
			Controller.running();
			
			Display.update();
			Display.sync(75);
		}
		Display.destroy();
	}

	public static void main(String[] args) {
		new Main();
	}

}
