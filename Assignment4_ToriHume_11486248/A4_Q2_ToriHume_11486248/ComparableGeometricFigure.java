package Assignment4_Q2;
//this is the ComparableGeometricFigure interface
//it uses generic to insure it can only be implemented objects that are subclasses of GeometricFigure2
public interface ComparableGeometricFigure<X extends GeometricFigure2> extends Comparable<X> {

}
