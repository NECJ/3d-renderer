//import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import render.*;
import matrix.*;

public class RenderDriver{

  public static void main(String[] args) throws Exception{

    // make the Scene
    Scene scene = new Scene();

    // make a triangles
    Shape2D triangle1 = new Shape2D(new Point[] {new Point(0, 0, 0),
                                                new Point(0, 2, 0),
                                                new Point(0, 0, 2)});

    Shape2D triangle2 = new Shape2D(new Point[] {new Point(0, 0, 0),
                                                new Point(0, 2, 0),
                                                new Point(2, 0, 0)});

    Shape2D triangle3 = new Shape2D(new Point[] {new Point(0, 0, 0),
                                                new Point(2, 0, 0),
                                                new Point(0, 0, 2)});

    Shape2D triangle4 = new Shape2D(new Point[] {new Point(2, 0, 0),
                                                new Point(0, 2, 0),
                                                new Point(0, 0, 2)});

    // make a triangles
    Shape2D triangle5 = new Shape2D(new Point[] {new Point(0, 0, 5),
                                                new Point(0, 2, 5),
                                                new Point(0, 0, 7)});

    Shape2D triangle6 = new Shape2D(new Point[] {new Point(0, 0, 5),
                                                new Point(0, 2, 5),
                                                new Point(2, 0, 5)});

    Shape2D triangle7 = new Shape2D(new Point[] {new Point(0, 0, 5),
                                                new Point(2, 0, 5),
                                                new Point(0, 0, 7)});

    Shape2D triangle8 = new Shape2D(new Point[] {new Point(2, 0, 5),
                                                new Point(0, 2, 5),
                                                new Point(0, 0, 7)});

    // make a cube
    Shape2D square1 = new Shape2D(new Point[] {new Point(4, 0, 0),
                                               new Point(6, 0, 0),
                                               new Point(6, 2, 0),
                                               new Point(4, 2, 0)});

    Shape2D square2 = new Shape2D(new Point[] {new Point(4, 0, 0),
                                                new Point(6, 0, 0),
                                                new Point(6, 0, 2),
                                                new Point(4, 0, 2)});

    Shape2D square3 = new Shape2D(new Point[] {new Point(4, 2, 0),
                                                new Point(6, 2, 0),
                                                new Point(6, 2, 2),
                                                new Point(4, 2, 2)});

    Shape2D square4 = new Shape2D(new Point[] {new Point(4, 0, 2),
                                                new Point(6, 0, 2),
                                                new Point(6, 2, 2),
                                                new Point(4, 2, 2)});

    Shape2D line1 = new Shape2D(new Point[] {new Point(0, 0, 0),
                                                new Point(0, 0, 20)});

    Shape2D line2 = new Shape2D(new Point[] {new Point(0, 0, 0),
                                                new Point(20, 0, 0)});

    Shape2D line3 = new Shape2D(new Point[] {new Point(0, 0, 0),
                                             new Point(0, 20, 0)});


    scene.add(triangle1);
    scene.add(triangle2);
    scene.add(triangle3);
    scene.add(triangle4);

    scene.add(triangle5);
    scene.add(triangle6);
    scene.add(triangle7);
    scene.add(triangle8);

    scene.add(square1);
    scene.add(square2);
    scene.add(square3);
    scene.add(square4);

    scene.add(line1);
    scene.add(line2);
    scene.add(line3);

    // top-level window with a title and a border which redners the scene
    SceneRenderer sceneRenderer = new SceneRenderer(scene);
    sceneRenderer.addKeyListener(new KeyListener(){
      public void keyPressed(KeyEvent e){;}
      public void keyReleased(KeyEvent e){;}
      public void keyTyped(KeyEvent e){
        switch(e.getKeyChar()){
          case 'w': scene.getCamera().moveFawardByDisplacment(0.5);
                    sceneRenderer.repaint();
                    break;
          case 's': scene.getCamera().moveFawardByDisplacment(-0.5);
                    sceneRenderer.repaint();
                    break;
          case 'a': scene.getCamera().moveRightByDisplacment(-0.5);
                    sceneRenderer.repaint();
                    break;
          case 'd': scene.getCamera().moveRightByDisplacment(0.5);
                    sceneRenderer.repaint();
                    break;
          case 'j': scene.getCamera().changeAngleRelative(0, 0.25);
                    sceneRenderer.repaint();
                    break;
          case 'l': scene.getCamera().changeAngleRelative(0, -0.25);
                    sceneRenderer.repaint();
                    break;
          case 'i': scene.getCamera().changeAngleRelative(0.25, 0);
                    sceneRenderer.repaint();
                    break;
          case 'k': scene.getCamera().changeAngleRelative(-0.25, 0);
                    sceneRenderer.repaint();
                    break;
        }
        System.out.println("X: "+scene.getCamera().getLocation().getX()+" Y: "+scene.getCamera().getLocation().getY()+" Z: "+scene.getCamera().getLocation().getZ());
      }

    });

    int[] mouseLocation = new int[2];
    sceneRenderer.addMouseMotionListener(new MouseMotionListener(){
      public void mouseDragged(MouseEvent e){
        int[] tempLoc = new int[]{(int)e.getPoint().getX(), (int)e.getPoint().getY()};
        e.translatePoint(-mouseLocation[0], -mouseLocation[1]);
        mouseLocation[0] = tempLoc[0];
        mouseLocation[1] = tempLoc[1];
        System.out.println("pitch: "+scene.getCamera().getPitch()+"Yaw: "+scene.getCamera().getYaw());
        scene.getCamera().changeAngleRelative(-e.getPoint().getY()/500.0, +e.getPoint().getX()/500.0);
        sceneRenderer.repaint();
      }
      public void mouseMoved(MouseEvent e){
        ;
      }
    });

    sceneRenderer.addMouseListener(new MouseListener(){
      public void mouseClicked(MouseEvent e){;}
      public void mouseEntered(MouseEvent e){;}
      public void mouseExited(MouseEvent e){;}
      public void mousePressed(MouseEvent e){
        mouseLocation[0] = (int)e.getPoint().getX();
        mouseLocation[1] = (int)e.getPoint().getY();
      }
      public void mouseReleased(MouseEvent e){
        mouseLocation[0] = (int)e.getPoint().getX();
        mouseLocation[1] = (int)e.getPoint().getY();
      }
    });

    // rotateSliderY.addChangeListener(e -> panel.repaint());
    // rotateSliderX.addChangeListener(e -> panel.repaint());
    sceneRenderer.setSize(1280,720);
    sceneRenderer.setRenderDistance(20);
    sceneRenderer.setScreenDistance(0.1);
    sceneRenderer.setVisible(true);
    sceneRenderer.setFocusableWindowState(true);
  }

}
