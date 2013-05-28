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

public class AcumlView {

  static {
    mxMarkerRegistry.registerMarker( "emptyTriangle", new mxIMarker() {
      @Override
      public mxPoint paintMarker( mxGraphics2DCanvas canvas, mxCellState state, String type, mxPoint pe, double nx, double ny, double size, boolean source ) {
        Polygon poly = new Polygon();
        poly.addPoint( (int)Math.round( pe.getX() ), (int)Math.round( pe.getY() ) );
        poly.addPoint( (int)Math.round( pe.getX() - nx - ny / 2 ), (int)Math.round( pe.getY() - ny + nx / 2 ) );
        poly.addPoint( (int)Math.round( pe.getX() + ny / 2 - nx ), (int)Math.round( pe.getY() - ny - nx / 2 ) );
        canvas.getGraphics().draw( poly );
        return new mxPoint( -nx, -ny );
      }
    } );
    //mxStencilRegistry.addStencil( "", new mxStencil() );
  }

//  private List<Lane> calcLifeLanes( AcumlModel model ) {
//    return null;
//  }
//
//  private List<Lane> calcTimeLanes( AcumlModel model ) {
//    return null;
//  }

  public static JComponent render( AcumlModel model ) {
//    mxCell edge = (mxCell)graph.insertEdge(defaultParent, null, "Edge", lifeHead1, lifeHead2);
//    edge.setStyle("startArrow=emptyTriangle;endArrow=emptyTriangle;startSize=12;endSize=12;");
    mxGraph graph = new mxGraph();
    Object root = graph.getDefaultParent();
    graph.getModel().beginUpdate();

    //List<Lane> entityLanes = calcLifeLanes( model );
    //List<Lane> timeLanes = calcTimeLanes( model );

    double entityMargin = 100.0;
    double entityWidth = 20.0;
    double entityHeight = 20.0;
    String entityStyle = "rounded=true;resizable=1;autosize=1";

    int entityIndex = 0;
    for( AcumlModel.Entity entity : model.getEntities() ) {
      mxCell cell = (mxCell)graph.insertVertex(
          root, entity.id, "",
          ( entityIndex * ( entityWidth + entityMargin ) ), 0,
          entityWidth, entityHeight,
          entityStyle );
      cell.setValue( entity.label );
      entityIndex++;
    }

    graph.getModel().endUpdate();
    mxGraphComponent graphComponent = new mxGraphComponent(graph);
    return graphComponent;
  }

  public static void main( String... args ) {
    mxGraph graph = new mxGraph();
    Object root = graph.getDefaultParent();
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
    Object lifeHead1 = graph.insertVertex( root, null, "Type\nOne", headMargin + ( lifeIndex * ( headSizeX + headMargin ) ), headMargin, headSizeX, headSizeY, "rounded=true" );
    Object lifeTail1 = graph.insertVertex( root, null, null, headMargin + ( lifeIndex * ( headSizeX + headMargin ) ) + headSizeX/2.0, headMargin + headSizeY + lifeSizeY, 0, 0, "" );
    Object lifeLine1 = graph.insertEdge( root, null, null, lifeHead1, lifeTail1, "startArrow=none;endArrow=none;dashed=true" );
    Object callBox1 = graph.insertVertex( root, null, null, headMargin + ( lifeIndex * ( headSizeX + headMargin ) ) + headSizeX/2.0 - (callSizeX/2.0), headMargin + headSizeY, callSizeX, 40, "" );

    lifeIndex = 1;
    double lifeX2 = 70;
    double lifeY2 = 20;
    Object lifeHead2 = graph.insertVertex( root, null, "Type\nTwo", headMargin + ( lifeIndex * ( headSizeX + headMargin ) ), headMargin, headSizeX, headSizeY, "rounded=true" );
    Object lifeTail2 = graph.insertVertex( root,  null, null, headMargin + ( lifeIndex * ( headSizeX + headMargin ) ) + headSizeX/2.0, headMargin + headSizeY + lifeSizeY, 0, 0, "" );
    Object lifeLine2 = graph.insertEdge( root, null, null, lifeHead2, lifeTail2, "startArrow=none;endArrow=none;dashed=true" );
    Object callBox2 = graph.insertVertex( root, null, null, headMargin + ( lifeIndex * ( headSizeX + headMargin ) ) + headSizeX/2.0 - (callSizeX/2.0), headMargin + headSizeY + 40, callSizeX, 40, "" );

    lifeIndex = 2;
    double lifeX3 = 130;
    double lifeY3 = 100;
    Object lifeHead3 = graph.insertVertex( root, null, "Type\nThree",  headMargin + ( lifeIndex * ( headSizeX + headMargin ) ), headMargin + lifeY3, headSizeX, headSizeY, "rounded=true" );
    Object lifeTail3 = graph.insertVertex( root,  null, null, headMargin + ( lifeIndex * ( headSizeX + headMargin ) ) + headSizeX/2.0, headMargin + lifeY3 + headSizeY + lifeSizeY, 0, 0, "" );
    Object lifeLine3 = graph.insertEdge( root, null, null, lifeHead3, lifeTail3, "startArrow=none;endArrow=none;dashed=true" );
    Object callBox3 = graph.insertVertex( root, null, null, headMargin + ( lifeIndex * ( headSizeX + headMargin ) ) + headSizeX/2.0 - (callSizeX/2.0), headMargin + lifeY3 + headSizeY + 80, callSizeX, 40, "" );

    graph.getModel().endUpdate();
    mxGraphComponent graphComponent = new mxGraphComponent(graph);

    JFrame frame = new JFrame( "Test renderLives");
    frame.getContentPane().add( graphComponent );
    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    frame.setSize( 600, 400 );
    frame.setLocationRelativeTo( null );
    frame.setVisible( true );
  }

  public static class Lane {
    double y;
    double h;
  }
}
