package net.minder.acuml;

import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.model.mxCell;
import com.mxgraph.shape.mxIMarker;
import com.mxgraph.shape.mxMarkerRegistry;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxPoint;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Acuml extends JFrame {

  public Acuml() {
    super("Acuml");

    //mxStencilRegistry.addStencil( "", new mxStencil() );
    mxMarkerRegistry.registerMarker( "emptyTriangle", new mxIMarker() {
      @Override
      public mxPoint paintMarker( mxGraphics2DCanvas canvas, mxCellState state, String type, mxPoint pe, double nx, double ny, double size, boolean source ) {
        Polygon poly = new Polygon();
        poly.addPoint( (int)Math.round( pe.getX() ), (int)Math.round( pe.getY() ) );
        poly.addPoint( (int)Math.round( pe.getX() - nx - ny / 2 ), (int)Math.round( pe.getY() - ny + nx / 2 ) );
        poly.addPoint( (int)Math.round( pe.getX() + ny / 2 - nx ), (int)Math.round( pe.getY() - ny - nx / 2 ) );
        canvas.getGraphics().draw( poly ); return new mxPoint( -nx, -ny );
      }
    } );

    mxGraph graph = new mxGraph();
    Object defaultParent = graph.getDefaultParent();
    graph.getModel().beginUpdate();

    double headSizeX = 60;
    double headSizeY = 40;
    double headSpace = 10;
    double headMargin = 10;
    double lifeSizeY = 200;
    double callSizeX = 10;

    double lifeIndex = 0;

    double lifeX1 = 10;
    double lifeY1 = 20;
    Object lifeHead1 = graph.insertVertex( defaultParent, null, "Type\nOne", headMargin + ( lifeIndex * ( headSizeX + headMargin ) ), headMargin, headSizeX, headSizeY, "rounded=true" );
    Object lifeTail1 = graph.insertVertex( defaultParent, null, null, headMargin + ( lifeIndex * ( headSizeX + headMargin ) ) + headSizeX/2.0, headMargin + headSizeY + lifeSizeY, 0, 0, "" );
    Object lifeLine1 = graph.insertEdge( defaultParent, null, null, lifeHead1, lifeTail1, "startArrow=none;endArrow=none;dashed=true" );
    Object callBox1 = graph.insertVertex( defaultParent, null, null, headMargin + ( lifeIndex * ( headSizeX + headMargin ) ) + headSizeX/2.0 - (callSizeX/2.0), headMargin + headSizeY, callSizeX, 40, "" );

    lifeIndex = 1;
    double lifeX2 = 70;
    double lifeY2 = 20;
    Object lifeHead2 = graph.insertVertex( defaultParent, null, "Type\nTwo", headMargin + ( lifeIndex * ( headSizeX + headMargin ) ), headMargin, headSizeX, headSizeY, "rounded=true" );
    Object lifeTail2 = graph.insertVertex( defaultParent,  null, null, headMargin + ( lifeIndex * ( headSizeX + headMargin ) ) + headSizeX/2.0, headMargin + headSizeY + lifeSizeY, 0, 0, "" );
    Object lifeLine2 = graph.insertEdge( defaultParent, null, null, lifeHead2, lifeTail2, "startArrow=none;endArrow=none;dashed=true" );
    Object callBox2 = graph.insertVertex( defaultParent, null, null, headMargin + ( lifeIndex * ( headSizeX + headMargin ) ) + headSizeX/2.0 - (callSizeX/2.0), headMargin + headSizeY + 40, callSizeX, 40, "" );

    lifeIndex = 2;
    double lifeX3 = 130;
    double lifeY3 = 100;
    Object lifeHead3 = graph.insertVertex( defaultParent, null, "Type\nThree",  headMargin + ( lifeIndex * ( headSizeX + headMargin ) ), headMargin + lifeY3, headSizeX, headSizeY, "rounded=true" );
    Object lifeTail3 = graph.insertVertex( defaultParent,  null, null, headMargin + ( lifeIndex * ( headSizeX + headMargin ) ) + headSizeX/2.0, headMargin + lifeY3 + headSizeY + lifeSizeY, 0, 0, "" );
    Object lifeLine3 = graph.insertEdge( defaultParent, null, null, lifeHead3, lifeTail3, "startArrow=none;endArrow=none;dashed=true" );
    Object callBox3 = graph.insertVertex( defaultParent, null, null, headMargin + ( lifeIndex * ( headSizeX + headMargin ) ) + headSizeX/2.0 - (callSizeX/2.0), headMargin + lifeY3 + headSizeY + 80, callSizeX, 40, "" );

//    mxCell edge = (mxCell)graph.insertEdge(defaultParent, null, "Edge", lifeHead1, lifeHead2);
//    edge.setStyle("startArrow=emptyTriangle;endArrow=emptyTriangle;startSize=12;endSize=12;");

    graph.getModel().endUpdate();
    mxGraphComponent graphComponent = new mxGraphComponent(graph);
    getContentPane().add(graphComponent);

//    Dimension d = graphComponent.getGraphControl().getSize();
//    BufferedImage image = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
//    Graphics2D g = image.createGraphics();
//    graphComponent.getGraphControl().paint(g);
//    final File outputfile = new File("test.png");
//    ImageIO.write( image, "png", outputfile );

  }

  public static void main(String args[]) {
    Acuml acuml = new Acuml();

//    Entity entityOne = acuml.addEntity( "Entity\nOne", "style" );
//    Life lifeOne = entityOne.startLife();
//
//    Entity entityTwo = acuml.addEntity( "Entity\nTwo", "style" );
//    Life lifeTwo = acuml.startLife( entityTwo );
//
//    Entity entityThree = acuml.addEntity( "Entity\nThree", "style" );
//    Life lifeThree1 = acuml.startLife( entityThree );
//    Life lifeThree2 = acuml.startLife( entityThree );
//
//    Msg callOne = acuml.startCall( lifeOne, lifeTwo, 5, "First Call", "style" );
//    acuml.endCall( callOne );
//
//    Msg callTwo = acuml.startCall( callOne, lifeTwo, 10, "Second Call", "style" );
//    acuml.endCall( callTwo );

    acuml.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    acuml.setSize( 400, 400 );
//    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//    frame.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    acuml.setLocationRelativeTo( null );
    acuml.setVisible( true );
  }
}