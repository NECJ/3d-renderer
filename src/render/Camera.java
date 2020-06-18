package render;
import java.lang.Math;

/** The Camera is the view point of the user in the scene
 * @author      Nathan Jones <nathanjones.ec@gmail.com>
 * @version     1.0
 * @since       1.0
 */

public class Camera{

  /** Represents the location of the Camera.*/
  private Point location;
  /** Represents direction the Camera is looking as a rotation around x axis.*/
  private double pitch;
  /** Represents direction the Camera is looking as a rotation around y axis.*/
  private double yaw;

  /**
 * Creates a Camera with location (0, 0, 0) facing along the z axis
 * @since 1.0
 */
  public Camera(){
    location = new Point(0, 0, 0);
    pitch = 0; // up in direction of z
    yaw = 0; // right in direction of Y
  }

  /**
 * Creates a Camera with a specific location facing along the z axis
 * @param  location The location for the Camera to be initialsed at.
 * @since 1.0
 */
  public Camera(Point location){
    this.location = location;
    pitch = 0; // up in direction of z
    yaw = 0; // right in direction of Y
  }

  /**
 * Creates a Camera with a specific location facing a specific direction.
 * @param  location The location for the Camera to be initialsed at.
 * @param  pitch The angle the Camera is facing up from the xz plane.
 * @param  yaw The angle the Camera is facing right from the yz plane.
 * @since 1.0
 */
  public Camera(Point location, double pitch, double yaw){
    this.location = new Point(0, 0, 0);
    this.pitch = pitch; // up in direction of z
    this.yaw = yaw; // right in direction of Y
  }

  /**
 * Moves the camera by an offset from the current location.
 * @param  x The size of the offset in the x direction.
 * @param  y The size of the offset in the y direction.
 * @param  z The size of the offset in the z direction.
 * @since 1.0
 */
  public void moveRelative(double x, double y, double z){
    location.setX(location.getX()+x);
    location.setY(location.getY()+y);
    location.setZ(location.getZ()+z);
  }

  /**
 * Moves the Camera to a specific location.
 * @param  x The x position to move the Camera to.
 * @param  y The y position to move the Camera to.
 * @param  z The z position to move the Camera to.
 * @since 1.0
 */
  public void setLocation(double x, double y, double z){
    location.setX(x);
    location.setY(y);
    location.setZ(z);
  }

  /**
 * Sets the Camera location.
 * @param  location The point you want to move the camera to.
 * @since 1.0
 */
  public void setLocation(Point location){
    this.location = location;
  }

  /**
 * Moves the Camera by a distance in the direction the camera is facing.
 * @param  x The displacment you want to move the camera by.
 * @since 1.0
 */
  public void moveFawardByDisplacment(double x){
    location.setX(location.getX()+x*Math.cos(pitch)*Math.sin(yaw));
    location.setY(location.getY()+x*Math.sin(pitch));
    location.setZ(location.getZ()+x*Math.cos(pitch)*Math.cos(yaw));
  }

  /**
 * Moves the Camera by a distance in the direction perpendicular to both direction the camera is facing and the y axis.
 * @param  x The displacment you want to move the camera by.
 * @since 1.0
 */
  public void moveRightByDisplacment(double x){
    double piOver2 = Math.PI/2;
    location.setX(location.getX()+x*Math.cos(pitch)*Math.sin(yaw+piOver2));

    location.setZ(location.getZ()+x*Math.cos(pitch)*Math.cos(yaw+piOver2));
  }

  /**
 * Gets the Camera Location.
 * @return The Location of the Camera.
 * @since 1.0
 */
  public Point getLocation(){
    return location;
  }

  /**
  * Changes the direction that the camera is facing by specific angles.
  * @param  x The anlge you want to turn around the x axis.
  * @param  y The anlge you want to turn around the y axis.
  * @since 1.0
  */
  public void changeAngleRelative(double x, double y){
    pitch += x;
    yaw += y;
  }

  /**
  * Sets the pitch (angle up from xz plane) angle.
  * @param  x The anlge you want to set the pitch (angle up from xz plane) as.
  * @since 1.0
  */
  public void setPitch(double x){
    pitch = x;
  }

  /**
  * Sets the yaw (angle right from yz plane) angle.
  * @param  x The anlge you want to set the pitch (angle right from yz plane) as.
  * @since 1.0
  */
  public void setYaw(double x){
    yaw = x;
  }

  /**
 * Gets the Camera pitch (angle up from xz plane).
 * @return The pitch (angle up from xz plane).
 * @since 1.0
 */
  public double getPitch(){
    return pitch;
  }

  /**
 * Gets the Camera yaw (angle right from yz plane).
 * @return The pitch (angle up from xz plane).
 * @since 1.0
 */
  public double getYaw(){
    return yaw;
  }

}
