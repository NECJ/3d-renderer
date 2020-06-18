package matrix;
import java.util.Arrays;
import java.util.Random;
import java.lang.Math;

/** This is a class to represent and perform operations on Matrices.
 * @author      Nathan Jones <nathanjones.ec@gmail.com>
 * @version     1.0
 * @since       1.0
 */

public class Matrix{
  private double matrix[][];

  /**
 * Constructs matrix of doubles with values 0.0 or random values between 1 and -1
 * @param  n Number of rows the Matrix has.
 * @param  m Number of columns the Matrix has.
 * @param  random Determines weather the matrix is to be populated with random
 * doubles from 1 to -1.
 * @since 1.0
 */
  public Matrix(int n, int m, Boolean random){
    matrix = new double[n][m];
    if(random){
      Random r = new Random();
      for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
          matrix[i][j] = 2*r.nextDouble()-1;
        }
      }
    }
  }

  /**
 * Constructs matrix of doubles with values 0.0
 * @param  n Number of rows the Matrix has.
 * @param  m Number of columns the Matrix has.
 * doubles from 1 to -1.
 * @since 1.0
 */
  public Matrix(int n, int m){
    matrix = new double[n][m];
  }

  /**
 * Constructs matrix with structure of double array.
 * @param  matrix A double array representaion of the Matrix.
 * @throws MatrixException Throws MatrixException is the input array is not rectangular
 * @since 1.0
 */
  public Matrix(double[][] matrix){
    float length = matrix[0].length;
    for(int i=1; i<matrix.length; i++){
      if(matrix[i].length != length){
        throw new MatrixException("Matrix has to be rectangular.");
      }
    }
    this.matrix = matrix;
  }

  /**
 * Returns the Matrix as a double array.
 * @return Matrix as a double array.
 * @since 1.0
 */
  public double[][] getMatrixAsArray(){
    return matrix;
  }

  /**
 * Gets any element of the Matrix
 * @param  n The row in which the element is.
 * @param  m The column in which the element is.
 * @return The element at location (n, m).
 * @since 1.0
 */
  public double get(int n, int m){
    return matrix[n][m];
  }

  /**
 * Sets the value of a given element
 * @param  n The row of the emement in which you want to change.
 * @param  m The column of the element in which you want to change.
 * @since 1.0
 */
  public void set(int n, int m, double x){
    matrix[n][m] = x;
  }

  /**
 * Returns the size of the matrix as an array of int.
 * @return Size of the matrix as an array of ints.
 * @since 1.0
 */
  public int[] getSize(){
    int[] size = {matrix.length, matrix[0].length};
    return size;
  }

   /**
  * Converts the Matrix into String form.
  * @return The Matrix in String from.
  * @since 1.0
  */
  public String toString(){
    String matrixString = Arrays.deepToString(matrix).replaceAll("\\Q], [\\E", "]\n[");
    return matrixString.substring(1, matrixString.length()-1);
  }

  /**
  * Returns the result of performing matrix addition on two matricies.
  * @param  x Matrix you want to add.
  * @param  y Matrix you want to add.
  * @return The sum of the two matricies x and y.
  * @throws MatrixException Throws MatrixException if the dimentions of the two
  * matricies dont match.
  * @since 1.0
  */
  public static Matrix add(Matrix x, Matrix y) throws MatrixException{
    if(x.getSize()[0] != y.getSize()[0] || x.getSize()[1] != y.getSize()[1]) {throw new MatrixException("Dimentions for add do not match");}
    else{
      Matrix newMatrix = new Matrix(x.getSize()[0], x.getSize()[1]);
      for(int i=0; i<x.getSize()[0]; i++){
        for(int j=0; j<x.getSize()[1]; j++){
          newMatrix.set(i,j,(x.get(i,j)+y.get(i,j)));
        }
      }
      return newMatrix;
    }
  }

  /**
  * Returns the result of performing matrix subtraction on two matricies.
  * @param  x Matrix you want to subtract.
  * @param  y Matrix you want to subtract.
  * @return The differnce between the two matricies x and y.
  * @throws MatrixException Throws MatrixException if the dimentions of the two
  * matricies dont match.
  * @since 1.0
  */
  public static Matrix sub(Matrix x, Matrix y) throws MatrixException{
    if(x.getSize()[0] != y.getSize()[0] || x.getSize()[1] != y.getSize()[1]) {throw new MatrixException("Dimentions for add do not match");}
    else{
      Matrix newMatrix = new Matrix(x.getSize()[0], x.getSize()[1]);
      for(int i=0; i<x.getSize()[0]; i++){
        for(int j=0; j<x.getSize()[1]; j++){
          newMatrix.set(i,j,(x.get(i,j)-y.get(i,j)));
        }
      }
      return newMatrix;
    }
  }

  /**
  * Returns the result of performing matrix multiplication on two matricies.
  * @param  x Matrix you want to multiply.
  * @param  y Matrix you want to multiply.
  * @return The product of the two matricies x and y.
  * @throws MatrixException Throws MatrixException if the dimentions of the two
  * matricies dont allow matrix multiplication.
  * @since 1.0
  */
  public static Matrix mult(Matrix x, Matrix y) throws MatrixException{
    if(x.getSize()[1] != y.getSize()[0]) {throw new MatrixException("Dimentions for mult do not match: ("+x.getSize()[0]+", "+x.getSize()[1]+"), ("+y.getSize()[0]+", "+y.getSize()[1]+")");}
    else{
      Matrix newMatrix = new Matrix(x.getSize()[0], y.getSize()[1]);
      for(int i=0; i<x.getSize()[0]; i++){
        for(int j=0; j<y.getSize()[1]; j++){
          double cell = 0;
          for(int k=0; k<x.getSize()[1]; k++){
            cell += x.get(i, k)*y.get(k, j);
          }
          newMatrix.set(i ,j ,cell);
        }
      }
      return newMatrix;
    }
  }

  /**
  * Returns the result of multipling a matrix my a scalar value.
  * @param  x Matrix you want to multiply.
  * @param  y The scarlar value you want to multiply the matrix by.
  * @return The new  of the two matricies x and y.
  * @since 1.0
  */
  public static Matrix scalarMult(Matrix x, double y){
    Matrix newMatrix = new Matrix(x.getSize()[0], x.getSize()[1]);
    for(int i=0; i<x.getSize()[0]; i++){
      for(int j=0; j<x.getSize()[1]; j++){
        newMatrix.set(i, j, x.get(i, j)*y);
      }
    }
    return newMatrix;
  }

  /**
  * Returns the transformation Matrix to perfrom a 3d rotation about the origin.
  * @param  radians The number of radians you want to rotate by.
  * @param  axis The axis ('x', 'y' or 'z') you want to rotate about.
  * @return The transformation Matrix corresponding to the parameters.
  * @since 1.0
  */
  public static Matrix make3DRotationMatrix(double radians, char axis){
    double sinTheta = Math.sin(radians);
    double cosTheta = Math.cos(radians);
    switch(axis){
      case 'x':
      case 'X': return new Matrix(new double[][]{{1, 0, 0},
                                                 {0, cosTheta, -sinTheta},
                                                 {0, sinTheta, cosTheta}});
      case 'y':
      case 'Y': return new Matrix(new double[][]{{cosTheta, 0, sinTheta},
                                                 {0, 1, 0},
                                                 {-sinTheta, 0, cosTheta}});
      case 'z':
      case 'Z': return new Matrix(new double[][]{{cosTheta, -sinTheta, 0},
                                                 {sinTheta, cosTheta, 0},
                                                 {0, 0, 1}});
      default: return null;
    }
  }
}
