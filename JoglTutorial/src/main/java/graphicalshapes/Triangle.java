package graphicalshapes;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;

public class Triangle implements GLEventListener {
    /**
     * угол поворота фигуры
     */
    private float rtri;

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
        /**
         * получаем объект типа {@link GL2}
         */
        final GL2 gl = drawable.getGL().getGL2();
        /**
         *
         */
        gl.glClear (GL2.GL_COLOR_BUFFER_BIT |  GL2.GL_DEPTH_BUFFER_BIT );
        /**
         * загружаем текущую матрицу объекта
         */
        gl.glLoadIdentity();
        /**
         * поворачиваем объект(фигуру)
         */
        gl.glRotatef(rtri, 0.0f, 1.0f, 0.0f);
        /**
         * увеличиваем разер фигуры
         */
        gl.glScalef(0.50f,0.25f,0.50f);
        /**
         * начало отрисовки фигуры на холсте, в аргументе указан тип фигуры
         */
        gl.glBegin (GL2.GL_TRIANGLES);
        /**
         * задаём цвет
         */
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(0.5f,0.7f,0.0f);

        gl.glColor3f( 0.0f,1.0f,0.0f );
        gl.glVertex3f( -0.2f,-0.50f,0.0f );

        gl.glColor3f( 0.0f,0.0f,1.0f );
        gl.glVertex3f( 0.5f,-0.5f,0.0f );

        /**
         * заканчиваем отрисовку фигуры
         */
        gl.glEnd();
        /**
         * подгружаем все данные в GPU, чтобы он отрисовал фигуру
         */
        gl.glFlush();
        rtri+= 0.2f;
        /**
         * активируем эффект света от источника на фигуру
         */
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_NORMALIZE);
        /**
         * задаём обтекание света
         */
        float[] ambientLight = {0.1f, 0.f, 0.f,0f};
        /**
         * применяем к фигуре
         */
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, ambientLight, 0);
        /**
         * задаём рассеивание света
         */
        float[] diffuseLight = {1f,2f,1f,0f};
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuseLight, 0);

    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        System.out.println("reshape");
    }

    public static void main(String[] args) {

        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        final GLCanvas glCanvas = new GLCanvas(capabilities);
        Triangle triangle = new Triangle();

        glCanvas.addGLEventListener(triangle);
        glCanvas.setSize(400,400);

        final JFrame frame = new JFrame("Triangle");
        frame.getContentPane().add(glCanvas);

        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
        /**
         * класс для воспроизведения анимации, присвоенной фигурам на холсте
         */
        final FPSAnimator animator = new FPSAnimator(glCanvas, 300, true);
        animator.start();
    }
}
