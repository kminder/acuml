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

import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AcumlViewTest {

  @Test
  public void testLives() throws InterruptedException {
    AcumlModel model = new AcumlModel();
    AcumlModel.Entity entity1 = model.addEntity( "test-entity-1", "test-entity-1-label" );
    AcumlModel.Entity entity2 = model.addEntity( "test-entity-2", "test-entity-2-label" );
    AcumlModel.Entity entity3 = model.addEntity( "test-entity-3", "test-entity-3-label" );

    model.startLife( entity1, "test-entity-1-life-1" );
    AcumlModel.Life entity2life1 = model.startLife( entity2, "test-entity-2-life-1" );
    model.tick();
    AcumlModel.Life entity3life1 = model.startLife( entity3, "test-entity-3-life-1" );
    model.tick();
    model.endLife( entity3life1 );
    model.tick();
    AcumlModel.Life entity3life2 = model.startLife( entity3, "test-entity-3-life-2" );
    model.tick();
    model.endLife( entity3life2 );
    model.endLife( entity2life1 );

    assertView( model, "renderLives" );
  }

  public void assertView( AcumlModel model, String test ) throws InterruptedException {
    JComponent view = AcumlView.render( model );
    CommandListener listener = new CommandListener();
    JButton passButton = new JButton( new CommandAction( "Pass" ) );
    passButton.addActionListener( listener );
    JButton failButton = new JButton( new CommandAction( "Fail" ) );
    failButton.addActionListener( listener );
    JFrame frame = new JFrame( test );
    frame.addWindowListener( listener );
    frame.getContentPane().setLayout( new FlowLayout() );
    frame.getContentPane().add( view );
    frame.getContentPane().add( passButton );
    frame.getContentPane().add( failButton );
    frame.setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
    frame.setSize( 600, 400 );
    frame.setLocationRelativeTo( null );
    frame.setVisible( true );
    String command = listener.waitForAction();
    assertThat( command, is( "Pass" ) );
  }

  public class CommandAction extends AbstractAction {
    public CommandAction( String name ) {
      super( name );
    }
    @Override
    public void actionPerformed( ActionEvent e ) {
    }
  };

  public class CommandListener extends WindowAdapter implements ActionListener {
    private AtomicReference<String> command = new AtomicReference<String>(null);
    @Override
    public void windowClosed( WindowEvent e ) {
      synchronized ( command ) {
        command.set( e.paramString() );
        command.notify();
      }
    }
    @Override
    public void actionPerformed( ActionEvent e ) {
      synchronized ( command ) {
        command.set( e.getActionCommand() );
        command.notify();
      }
    }
    public String waitForAction() throws InterruptedException {
      synchronized ( command ) {
        while( command.get() == null ) {
          command.wait();
        }
      }
      return command.get();
    }
  };

}
