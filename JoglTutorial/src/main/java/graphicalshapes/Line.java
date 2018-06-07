package graphicalshapes;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;

import javax.swing.*;
import java.awt.*;

public class Line implements GLEventListener {

    @Override
    public void init(GLAutoDrawable drawable) {
        System.out.println("init");
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        System.out.println("dispose");
    }

    @Override
    public void display(GLAutoDrawable drawable) {


        final GL2 gl = drawable.getGL().getGL2();

        gl.glBegin(GL2.GL_LINES);
        gl.glVertex3f(0.5f, -0.5f, 0);
        gl.glVertex3f(-0.5f, 0.5f, 0);
        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        System.out.println("reshape");
    }

    public static void main(String[] args) {
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        final GLCanvas glCanvas = new GLCanvas(capabilities);
        Line line = new Line();
        glCanvas.addGLEventListener(line);
        glCanvas.setSize(new Dimension(400, 400));

        final JFrame frame = new JFrame("Line");

        frame.getContentPane().add(glCanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
    }
}
