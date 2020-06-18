package render;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.awt.Color;
import matrix.*;

/** A Shape2D represent a 2D shape made up of Points in 3D space.
* @author      Nathan Jones <nathanjones.ec@gmail.com>
* @version     1.0
* @since       1.0
*/

public class Shape2D{

  /** An Array List of each vertex of the shape.*/
  private ArrayList<Point> vertexes;
  /** The color of the shape.*/
  private Color color;

  /**
 * Creates a Shape2D with a default color of Color.WHITE.
 * @param  points A ArrayList of points which represnet the points of the shape
 * and the order determines where the lines are drawn.
 * @since 1.0
 */
  public Shape2D(ArrayList<Point> points){
    vertexes = new ArrayList<Point>();
    vertexes = points;
    color = Color.WHITE;
  }

  /**
 * Creates a Shape2D with a default color of Color.WHITE.
 * @param  points An array of points which represnet the points of the shape
 * and the order determines where the lines are drawn.
 * @since 1.0
 */
  public Shape2D(Point[] points){
    vertexes = new ArrayList<Point>();
    Collections.addAll(vertexes, points);
    color = Color.WHITE;
  }

  /**
 * Creates a Shape2D with a specific Color.
 * @param  points A ArrayList of points which represnet the points of the shape
 * and the order determines where the lines are drawn.
 * @param  color The color of the shape.
 * @since 1.0
 */
  public Shape2D(ArrayList<Point> points, Color color){
    vertexes = new ArrayList<Point>();
    vertexes = points;
    this.color = color;
  }

  /**
 * Creates a Shape2D with a specific Color.
 * @param  points An array of points which represnet the points of the shape
 * and the order determines where the lines are drawn.
 * @param  color The color of the shape.
 * @since 1.0
 */
  public Shape2D(Point[] points, Color color){
    vertexes = new ArrayList<Point>();
    Collections.addAll(vertexes, points);;
    this.color = color;
  }

  /**
  * Transforms the shape by a transformation matrix.
  * @param  m The transformation matrix to be applied to the shape.
  * @since 1.0
  */
  public void transform(Matrix m){
    for(Point vertex : vertexes){
      vertex.transform(m);
    }
  }

  /**
 * Returns the Shape2D after being translated but dose not alter the original
 * Shape2D. <p>First it is transformed by the negative offset then rotated about
 * the y and x axis.<\p>
 * @param  offset The amount the shape should be offset by.
 * @param  angleAboutY The angle you want to rotate.
 * @param  angleAboutX The amount the shape should be offset by.
 * @return TShape2D after being translated.
 * @since 1.0
 */
  public Shape2D getTransformedShape(Point offset, double angleAboutX, double angleAboutY){
    double sinX = Math.sin(angleAboutX);
    double cosX = Math.cos(angleAboutX);
    double sinY = Math.sin(angleAboutY);
    double cosY = Math.cos(angleAboutY);
    Matrix transformationMatrix = new Matrix(new double[][]{
      {cosY, 0, sinY, -offset.getX()*cosY-offset.getZ()*sinY},
      {sinX*sinY, cosX, -sinX*cosY, -offset.getX()*sinX*sinY-offset.getY()*cosX+offset.getZ()*sinX*cosY},
      {-cosX*sinY, sinX, cosX*cosY, offset.getX()*cosX*sinY-offset.getY()*sinX-offset.getZ()*cosX*cosY},
      {0, 0, 0, 1}
    });
    ArrayList<Point> newVertexes = new ArrayList(vertexes);
    for(int i=0; i<newVertexes.size(); i++){
      newVertexes.set(i, newVertexes.get(i).clone()) ;
    }

    Shape2D newShape = new Shape2D(newVertexes, color);
    newShape.transform(transformationMatrix);
    return newShape;
  }

  /**
 * Returns the Shape2D after being translated by matrix m but dose not alter the
 * original Shape2D.
 * @param  m The matrix to transform the shape by.
 * @return TShape2D after being translated.
 * @since 1.0
 */
  public Shape2D getTransformedShape(Matrix m){
    ArrayList<Point> newVertexes = new ArrayList(vertexes);
    for(int i=0; i<newVertexes.size(); i++){
      newVertexes.set(i, newVertexes.get(i).clone()) ;
    }

    Shape2D newShape = new Shape2D(newVertexes, color);
    newShape.transform(m);
    return newShape;
  }

  /**
  * Returns an ArrayList of the shapes vertexes after being transformed but
  * does not perminantly transform the shape.
  * @param  m The transformation matrix to be applied to the shape.
  * @return The translated vertexes of the shape.
  * @since 1.0
  */
  public ArrayList<Point> getTransformedVertexes(Matrix m){
    ArrayList<Point> newVertexes = new ArrayList<Point>();
    for(Point vertex : vertexes){
      newVertexes.add(vertex.getTransformedPoint(m));
    }
    return newVertexes;
  }

  /**
  * Gets a specific vertex by index.
  * @param  i The index of the vertex.
  * @return The vertex as a Point.
  * @since 1.0
  */
  public Point getVertex(int i){
    return vertexes.get(i);
  }

  /**
  * Gets all vertexes of the Shape2D.
  * @return The vertexes of the Shape2D as an ArrayList.
  * @since 1.0
  */
  public ArrayList<Point> getVertexes(){
    return vertexes;
  }

  /**
  * Returns the number of vertexes that the shape has.
  * @return The number of vertexes that the shape has.
  * @since 1.0
  */
  public int getNumberOfVertexes(){
    return vertexes.size();
  }
}
