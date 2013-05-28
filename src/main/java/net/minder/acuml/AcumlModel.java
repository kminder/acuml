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

import com.mxgraph.model.mxCell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AcumlModel {

  public static class Entity {
    String id;
    String label;
    List<Life> lives = new ArrayList<Life>();
  }

  public static class Life {
    Entity entity;
    String id;
    int birthTime;
    int deathTime;
    mxCell head;
    mxCell tail;
    mxCell line;
    List<List<Act>> acts = new ArrayList<List<Act>>();
  }

  public static class Act {
    int startTime;
    int stopTime;
    Life life;
    mxCell box;
  }

  public static class Call {
    int startTime;
    int stopTime;
  }

  public static class Msg {
    String label;
    int time;
    mxCell arrow;
    Life sourceLife;
    Act sourceAct;
    mxCell sourcePort;
    Life targetLife;
    Act targetAct;
    mxCell targetPort;
  }

  private int time = 0;
  private Map<String,Entity> entities = new LinkedHashMap<String,Entity>();
  private Map<String,Life> lives = new LinkedHashMap<String,Life>();
  private List<List<Msg>> calls = new ArrayList<List<Msg>>();
  private Map<String,Integer> times = new HashMap<String,Integer>();

  public int time() {
    return time;
  }

  public int tick() {
    return ++time;
  }

  public Collection<Entity> getEntities() {
    return entities.values();
  }

  public Entity addEntity( String id, String label ) {
    Entity entity = new Entity();
    entity.id = id;
    entity.label = label;
    entities.put( id, entity );
    return entity;
  }

  public Entity getEntity( String id ) {
    return entities.get( id );
  }

  public Collection<Life> getLives() {
    return lives.values();
  }

  public Life getLife( String id ) {
    return lives.get( id );
  }

  public Life startLife( Entity entity, String id ) {
    Life life = new Life();
    life.entity = entity;
    life.id = id;
    life.birthTime = time();
    life.deathTime = -1;
    lives.put( id, life );
    entity.lives.add( life );
    return life;
  }

  public void endLife( Life life ) {
    life.deathTime = time();
  }

  public Call startCall( Life sourceLife, Entity targetEntity ) {
    Call call = new Call();
    call.startTime = time();
    return call;
  }

  public void endCall( Call call ) {
    call.stopTime = time();
  }

}
