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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;

public class AcumlModelTest {

  @Before
  public void setUp() throws Exception {

  }

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public void testTime() {
    AcumlModel model = new AcumlModel();
    assertThat( model.time(), is( 0 ) );
    assertThat( model.tick(), is( 1 ) );
    assertThat( model.time(), is( 1 ) );
  }

  @Test
  public void testEntity() {
    AcumlModel model = new AcumlModel();
    assertThat( model.getEntities(), hasSize( 0 ) );
    AcumlModel.Entity entity = model.addEntity( "test-entity-id", "test-entity-label" );
    assertThat( entity, notNullValue() );
    assertThat( entity.id, is( "test-entity-id" ) );
    assertThat( entity.label, is( "test-entity-label" ) );
    assertThat( entity.lives, hasSize( 0 ) );
    assertThat( model.getEntity( "test-entity-id" ), sameInstance( entity ) );
    assertThat( model.getEntities(), hasSize( 1 ) );
    assertThat( model.getEntities().iterator().next(), sameInstance( entity) );
  }

  @Test
  public void testLife() {
    AcumlModel model = new AcumlModel();
    AcumlModel.Entity entity = model.addEntity( "test-entity-id", "test-entity-label" );
    AcumlModel.Life life = model.startLife( entity, "test-life-id" );
    assertThat( life, notNullValue() );
    assertThat( life.entity, sameInstance( entity ) );
    assertThat( life.id, is( "test-life-id" ) );
    assertThat( life.birthTime, is( 0 ) );
    assertThat( life.deathTime, is( -1 ) );
    model.tick();
    model.endLife( life );
    assertThat( life.deathTime, is( 1 ) );

    assertThat( model.getLife( "test-life-id" ), sameInstance( life ) );
    assertThat( model.getLives(), hasSize( 1 ) );
    assertThat( model.getLives().iterator().next(), sameInstance( life ) );
  }

  @Test
  public void testCall() {
    AcumlModel model = new AcumlModel();
    AcumlModel.Entity sourceEntity = model.addEntity( "test-source-entity-id", "test-entity-label" );
    AcumlModel.Entity targetEntity = model.addEntity( "test-target-entity-id", "test-entity-label" );
    AcumlModel.Life sourceLife = model.startLife( sourceEntity, "test-source-life-id" );
    AcumlModel.Call call = model.startCall( sourceLife, targetEntity );
    assertThat( call, notNullValue() );
    assertThat( call.startTime, is( 0 ) );
    model.tick();
    model.endCall( call );
    assertThat( call.stopTime, is( 1 ) );
  }

  @Test
  public void testFirst() {

//    AcumlModel model = new AcumlModel();
//
//    AcumlModel.Entity entityOne = model.addEntity( "Entity\nOne", "style" );
//    AcumlModel.Life lifeOne = entityOne.startLife();
//
//    AcumlModel.Entity entityTwo = model.addEntity( "Entity\nTwo", "style" );
//    AcumlModel.Life lifeTwo = model.startLife( entityTwo );
//
//    AcumlModel.Entity entityThree = model.addEntity( "Entity\nThree", "style" );
//    model.tick();
//    AcumlModel.Life lifeThree1 = model.startLife( entityThree );
//    model.tick();
//    AcumlModel.Life lifeThree2 = model.startLife( entityThree );
//
//    model.tick();
//    AcumlModel.Msg callOne = model.startCall( lifeOne, lifeTwo, 5, "First Call", "style" );
//    model.tick();
//    model.endCall( callOne );
//
//    model.tick();
//    AcumlModel.Msg callTwo = model.startCall( callOne, lifeTwo, 10, "Second Call", "style" );
//    model.tick();
//    model.endCall( callTwo );

  }

}
