import java.awt.geom.*; // for Point2D.Double
import java.util.ArrayList; // for ArrayList
import gpdraw.*; // for DrawingTool

public class IrregularPolygon {
    private ArrayList<Point2D.Double> myPolygon = new ArrayList<Point2D.Double>();

    public IrregularPolygon() {}

    public void add(Point2D.Double aPoint) {
        myPolygon.add(aPoint);
    }

    public double perimeter() {
        double perimeter = 0.0;
        for (int i = 0; i < myPolygon.size(); i++) {
            Point2D.Double current = myPolygon.get(i);
            Point2D.Double next = myPolygon.get((i + 1) % myPolygon.size());
            
            double distance = current.distance(next);
            perimeter += distance;
        }
        return perimeter;
    }

    public double area() {
        double area = 0.0;
        int n = myPolygon.size();
        
        for (int i = 0; i < n; i++) {
            Point2D.Double p1 = myPolygon.get(i);
            Point2D.Double p2 = myPolygon.get((i + 1) % n);
            
            area += p1.getX() * p2.getY();
            area -= p1.getY() * p2.getX();
        }
        area = Math.abs(area) / 2.0;
        return area;
    }

    public void draw() {
        try {
            DrawingTool pen = new DrawingTool(new SketchPad(500, 500));
            Point2D.Double firstPoint = myPolygon.get(0);
            pen.move(firstPoint.getX(), firstPoint.getY());
            pen.down();
            
            for (int i = 1; i < myPolygon.size(); i++) {
                Point2D.Double point = myPolygon.get(i);
                pen.move(point.getX(), point.getY());
            }

            pen.move(firstPoint.getX(), firstPoint.getY());
            pen.up();
        } catch (java.awt.HeadlessException e) {
            System.out.println("Exception: No graphics support available.");
        }
    }
}
