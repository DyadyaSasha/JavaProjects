package basictemplate;

import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import javax.swing.*;

/**
 * {@link GLEventListener} описывает методы жизненного цикла и отрисовки OpenGl объекта(фигуры)
 */
public class BasicFrameAWT implements GLEventListener{
    /**
     * вызывается, чтобы отчистить ресурсы, связанные с OpenGL контекстом
     */
    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {
        System.out.println("dispose");
    }

    /**
     * вызывается, когда инициализуруется контекст OpenGL
     * в нём можно указать фигуры для отрисовки, свет, анимацию и т.п.
     */
    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        System.out.println("init");
    }

    /**
     * вызывается, чтобы отрисовать заданные фигуры
     */
    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        System.out.println("display");
    }

    /**
     * вызывается фигурой, когда меняется её размер
     */
    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
        System.out.println("reshape");
    }

    public static void main(String[] args) {
        /**
         *
         */
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        /**
         *
         */
        GLCapabilities capabilities = new GLCapabilities(profile);
        /**
         *
         */
        final GLCanvas glCanvas = new GLCanvas(capabilities);
        GLEventListener basicFrame = new BasicFrameAWT();
        /**
         *
         */
        glCanvas.addGLEventListener(basicFrame);
        glCanvas.setSize(400,400);
        final JFrame frame = new JFrame("Basic Frame");
        frame.getContentPane().add(glCanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
    }
}
