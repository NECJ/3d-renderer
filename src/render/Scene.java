package render;
import java.util.ArrayList;

/** A Scene is an object that has a camera and a list of Shape2D objects.
 * @author      Nathan Jones <nathanjones.ec@gmail.com>
 * @version     1.0
 * @since       1.0
 */

public class Scene{

  ArrayList<Shape2D> shapes2D;
  Camera camera;

  /**
  * Creates a Scene with a Camera location at the origin facing along the z axis.
  * @since 1.0
  */
  public Scene(){
    camera = new Camera();
    shapes2D = new ArrayList<Shape2D>();
  }

  /**
  * Creates a Scene with a specific Camera location, pitch and yaw.
  * @param  x The x coordinate of the Point.
  * @param  y The y coordinate of the Point.
  * @param  z The z coordinate of the Point.
  * @since 1.0
  */
  public Scene(Point cameraLocation, double pitch, double yaw){
    camera = new Camera(cameraLocation, pitch, yaw);
    shapes2D = new ArrayList<Shape2D>();
  }

  /**
  * Adds a Shape2D to the scene.
  * @param  shape The shape2D that you want to add to the Scene
  * @since 1.0
  */
  public void add(Shape2D shape){
    shapes2D.add(shape);
  }

  /**
  * Adds multiple Shape2Ds to the scene.
  * @param  shapes An itterable object that holds shape2Ds that you want to add to the Scene.
  * @since 1.0
  */
  public void add(Iterable<Shape2D> shapes){
    for(Shape2D shape : shapes){
      shapes2D.add(shape);
    }
  }

  /**
 * Gets the Camera in the Scene.
 * @return The Camera in the Scene.
 * @since 1.0
 */
  public Camera getCamera(){
    return camera;
  }

  /**
 * Gets the Shape2Ds in the Scene.
 * @return The Shape2Ds in the Scene as a ArrayList.
 * @since 1.0
 */
  public ArrayList<Shape2D> getShapes2D(){
    return shapes2D;
  }

}
