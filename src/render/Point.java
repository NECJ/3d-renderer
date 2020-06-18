package render;
import matrix.*;
import java.lang.Math;

/** A Point represents a location in a 3D coordinate space.
 * @author      Nathan Jones <nathanjones.ec@gmail.com>
 * @version     1.0
 * @since       1.0
 */

public class Point{
  private double x;
  private double y;
  private double z;
  private Matrix homoCoord;

  /**
  * Creates a Point with coordinates (0, 0, 0).
  * @since 1.0
  */
  public Point(){
     x = 0;
     y = 0;
     z = 0;
     homoCoord = new Matrix(new double[][]{{0}, {0}, {0}, {1}});
  }

 /**
 * Creates a Point with a specific location.
 * @param  x The x coordinate of the Point.
 * @param  y The y coordinate of the Point.
 * @param  z The z coordinate of the Point.
 * @since 1.0
 */
  public Point(double x, double y, double z){
    this.x = x;
    this.y = y;
    this.z = z;
    homoCoord = new Matrix(new double[][]{{x}, {y}, {z}, {1}});
  }

  /**
  * Creates a Point with a specific location.
  * @param  homoCoord A 4x1 Matrix that represents the homogeneous representaion of the coordinate.
  * @since 1.0
  */
  private Point(Matrix homoCoord){
    x = homoCoord.get(0,0);
    y = homoCoord.get(1,0);
    z = homoCoord.get(2,0);
    this.homoCoord = homoCoord;
  }

  /**
 * Gets the 4x1 Matrix representaion of the homogeneous coordinate.
 * @return The 4x1 Matrix representaion of the homogeneous coordinate.
 * @since 1.0
 */
  public Matrix getPointAsMatrix(){
    return homoCoord;
  }

  /**
 * Gets the x coordinate of the Point.
 * @return The x coordinate of the Point.
 * @since 1.0
 */
  public double getX(){
    return x;
  }

  /**
 * Gets the y coordinate of the Point.
 * @return The y coordinate of the Point.
 * @since 1.0
 */
  public double getY(){
    return y;
  }

  /**
 * Gets the z coordinate of the Point.
 * @return The z coordinate of the Point.
 * @since 1.0
 */
  public double getZ(){
    return z;
  }

  /**
  * Sets the Points coordinates.
  * @param  coord A 3x1 Matrix representaion of the cartesian coordinates or
  * a 4x1 Matrix representaion of the homogeneous coordinate.
  * @since 1.0
  */
  public void setPoint(Matrix coord){
    this.homoCoord = coord;
    x = coord.get(0,0);
    y = coord.get(1,0);
    z = coord.get(2,0);
  }

  /**
  * Sets the x coordinate or the Point.
  * @param  x The x coordinate you want to set.
  * @since 1.0
  */
  public void setX(double x){
    this.x = x;
    this.homoCoord.set(0, 0, x);
  }

  /**
  * Sets the y coordinate or the Point.
  * @param  x The y coordinate you want to set.
  * @since 1.0
  */
  public void setY(double y){
    this.y = y;
    this.homoCoord.set(1, 0, y);
  }

  /**
  * Sets the z coordinate or the Point.
  * @param  x The z coordinate you want to set.
  * @since 1.0
  */
  public void setZ(double z){
    this.z = z;
    this.homoCoord.set(2, 0, z);
  }

  /**
  * Transforms the point by a Matrix.
  * @param  m The transformation Matrix that represents the transformation you want to take place.
  * @since 1.0
  */
  public void transform(Matrix m){
    homoCoord = Matrix.mult(m, homoCoord);
    x = homoCoord.get(0,0);
    y = homoCoord.get(1,0);
    z = homoCoord.get(2,0);
  }

  /**
  * Returns the Point after being transformed without altering the original Point
  * @param  m The transformation Matrix that represents the transformation you want to take place.
  * @since 1.0
  */
  public Point getTransformedPoint(Matrix m){
    Point newPoint = new Point(Matrix.mult(m, homoCoord));
    return newPoint;
  }

  /**
  * Returns a new Point which has a location as if the pointToBeRelativeTo is the origin (0, 0, 0).
  * @param  pointToBeRelativeTo The point to be considered as the new origin.
  * @since 1.0
  */
  public Point getRelativePoint(Point pointToBeRelativeTo){
     return new Point(Matrix.sub(this.getPointAsMatrix(), pointToBeRelativeTo.getPointAsMatrix()));
  }

  /**
  * Gets the absolute distance to another point.
  * @param  pointToGetDistanceTo The point find the distance to.
  * @return The distance to that point form this point.
  * @since 1.0
  */
  public double getDistanceToPoint(Point pointToGetDistanceTo){
     Point fromOrigin = getRelativePoint(pointToGetDistanceTo);
     return Math.sqrt(Math.pow(fromOrigin.getX(), 2)+Math.pow(fromOrigin.getY(), 2)+Math.pow(fromOrigin.getZ(), 2));
  }

  /**
  * Returns a string representaion of the point in form "(x, y, z)".
  * @return A string representaion of the point in form "(x, y, z)".
  * @since 1.0
  */
  public String toString(){
    return "("+x+", "+y+", "+z+")";
  }

  /**
  * Creates a new Point with the same location as the original.
  * @return A new Point with the same location as the original.
  * @since 1.0
  */
  public Point clone(){
    return new Point(x, y, z);
  }
}
