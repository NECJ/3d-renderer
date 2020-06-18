package render;
import java.awt.*;
import javax.swing.*;
import matrix.*;
import java.util.ArrayList;

/** A SceneRenderer is a extention of a JFrame that renders a Scene.
 * @author      Nathan Jones <nathanjones.ec@gmail.com>
 * @version     1.0
 * @since       1.0
 */

public class SceneRenderer extends JFrame{

  private Scene scene;
  private double renderDistance;
  private double screenDistance;
  private double fov;

  /**
 * Creates a SceneRenderer.
 * @param  scene A scene that you want rendered.
 * @since 1.0
 */
  public SceneRenderer(Scene scene){
    super();
    this.scene = scene;
    renderDistance = 20;
    screenDistance = 1;
    fov= (1/2.0)*Math.PI;
  }

  @Override
  public void paint(Graphics g){
    double sh = getSize().getHeight();
    double sw = getSize().getWidth();
    double ar = sh/sw;
    double fovNormaliser = 1/Math.tan(fov/2);
    double zNormaliser = (renderDistance/(renderDistance/screenDistance));
    Graphics2D g2d = (Graphics2D) g;

    // set background color to black
    g2d.setColor(Color.BLACK);
    g2d.fillRect(0, 0, getWidth(), getHeight());

    // get camera getLocation
    Point cameraLocation = scene.getCamera().getLocation();

    // draw white lines to show outlines of shapes
    g2d.setColor(Color.WHITE);
    for(Shape2D shape : scene.getShapes2D()){
      // work out relative coordinates
      Shape2D relativeShape = shape.getTransformedShape(cameraLocation, scene.getCamera().getPitch(), -scene.getCamera().getYaw());

      // work out normalised coordinates
      int numberOfVertexes = relativeShape.getNumberOfVertexes();
      ArrayList<Point> normalisedPoints = relativeShape.getTransformedVertexes(new Matrix(new double[][]{
        {ar*fovNormaliser, 0.0, 0.0, 0.0},
        {0.0, ar*fovNormaliser, 0.0, 0.0},
        {0.0, 0.0, zNormaliser, -zNormaliser*screenDistance},
        {0.0, 0.0, 1.0, 0.0}
      }));
      for(Point point : normalisedPoints){
        point.setPoint(Matrix.scalarMult(point.getPointAsMatrix(), 1/point.getPointAsMatrix().get(3,0)));
      }
      for(int i=0; i<numberOfVertexes; i++){
        if(i == numberOfVertexes-1){
          if(relativeShape.getVertex(i).getZ()>0 && relativeShape.getVertex(0).getZ()>0){
            g2d.drawLine((int)(sw/2+sw*normalisedPoints.get(i).getX()), (int)(sh/2-sh*normalisedPoints.get(i).getY()),
                         (int)(sw/2+sw*normalisedPoints.get(0).getX()), (int)(sh/2-sh*normalisedPoints.get(0).getY()));
          }
        }else if(relativeShape.getVertex(i).getZ()>0 && relativeShape.getVertex(i+1).getZ()>0){
          g2d.drawLine((int)(sw/2+sw*normalisedPoints.get(i).getX()), (int)(sh/2-sh*normalisedPoints.get(i).getY()),
                       (int)(sw/2+sw*normalisedPoints.get(i+1).getX()), (int)(sh/2-sh*normalisedPoints.get(i+1).getY()));
        }
      }
    }
    g2d.fillOval((int)(sw/2), (int)(sh/2), 5 ,5);
  }


  @Override
  public void update(Graphics g){
    paint(g);
  }

  /**
 * Sets the render distance.
 * @param  x The render distance.
 * @since 1.0
 */
  public void setRenderDistance(double x){
    this.renderDistance = x;
  }

  /**
 * Sets the screen distance.
 * @param  x The screen distance.
 * @since 1.0
 */
  public void setScreenDistance(double x){
    this.screenDistance = x;
  }

}
