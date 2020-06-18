# 3d-renderer
This is a 3D renderer written in java.

The `render` package holds classes to allow creation and representaion of a `Scene` and has a class `SceneRenderer` which extends `javax.swing.JFrame` to render the `Scene`.

The `matrix` package has two classes. One of them is used for a basic representaion of a `Matrix` and has methods to perfrom operations on those Matrixes. The other class is an extention of a Java `Exception` which is thrown at many points within the `Matrix` class.
