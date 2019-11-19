/*
 * Copyright (c) 2017-2019 "Neo4j,"
 * Neo4j Sweden AB [http://neo4j.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package good;

import org.neo4j.graphalgo.core.CypherMapWrapper;

import javax.annotation.Generated;

@Generated("org.neo4j.graphalgo.proc.ConfigurationProcessor")
public final class ConversionsConfig implements Conversions.MyConversion {

    private final int directMethod;

    private final int inheritedMethod;

    private final int qualifiedMethod;

    public ConversionsConfig(CypherMapWrapper config) {
        this.directMethod = Conversions.MyConversion.toInt(config.requireString("directMethod"));
        this.inheritedMethod = Conversions.BaseConversion.toIntBase(config.requireString("inheritedMethod");
        this.qualifiedMethod = Conversions.OtherConversion.toIntQual(config.requireString("qualifiedMethod");
    }

    public ConversionsConfig(int directMethod, int inheritedMethod, int qualifiedMethod) {
        this.directMethod = directMethod;
        this.inheritedMethod = inheritedMethod;
        this.qualifiedMethod = qualifiedMethod;
    }

    @Override
    public int directMethod() {
        return this.directMethod;
    }

    @Override
    public int inheritedMethod() {
        return this.inheritedMethod;
    }

    @Override
    public int qualifiedMethod() {
        return this.qualifiedMethod;
    }
}