/*
 * Copyright 2017 ObjectBox Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hjq.modular;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Generated;
import io.objectbox.annotation.Id;

// THIS CODE IS GENERATED BY ObjectBox, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity "ObfuscatedEntity".
 */
@Entity
public class ObfuscatedEntity {

    @Id
    private long id;
    private int myInt;
    private String myString;

    @Generated
    public ObfuscatedEntity() {
    }

    public ObfuscatedEntity(long id) {
        this.id = id;
    }

    @Generated
    public ObfuscatedEntity(long id, int myInt, String myString) {
        this.id = id;
        this.myInt = myInt;
        this.myString = myString;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMyInt() {
        return myInt;
    }

    public void setMyInt(int myInt) {
        this.myInt = myInt;
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }

}