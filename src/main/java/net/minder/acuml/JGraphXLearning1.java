/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.minder.acuml;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.model.mxCell;
import com.mxgraph.shape.mxIMarker;
import com.mxgraph.shape.mxMarkerRegistry;
import com.mxgraph.shape.mxStencil;
import com.mxgraph.shape.mxStencilRegistry;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxPoint;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * First examples of JGraphX - Creating a simple frame that
 * contains a graph component with two vertices and an edge connecting them.
 * @author vainolo
 */
public class JGraphXLearning1 extends JFrame {

  private static final long serialVersionUID = 196831535599934813L;

  public JGraphXLearning1() {
    super("JGraphXLearning1");

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
    Object v1 = graph.insertVertex(defaultParent, null, "Hello\nHello", 20, 20, 80, 30);
    Object v2 = graph.insertVertex(defaultParent, null, "World", 240, 150, 80, 30);

    mxCell edge = (mxCell)graph.insertEdge(defaultParent, null, "Edge", v1, v2);
    edge.setStyle("startArrow=emptyTriangle;endArrow=emptyTriangle;startSize=12;endSize=12;");

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
    JGraphXLearning1 frame = new JGraphXLearning1();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 320);
    frame.setVisible(true);
  }
}