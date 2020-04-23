/*
 * Copyright (c) 2017-2020 "Neo4j,"
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
package org.neo4j.graphalgo.core.loading;

import org.neo4j.graphdb.GraphDatabaseService;

public interface StoreScanner<Record> {

    int DEFAULT_PREFETCH_SIZE = 100;

    interface RecordConsumer<Record> {
        /**
         * Imports the record at a given position and return the new position.
         * Can also ignore the record if it is not of interest.
         */
        void offer(Record record);
    }

    interface Factory<Record> {
        StoreScanner<Record> newScanner(int prefetchSize, GraphDatabaseService api);
    }

    interface Cursor<Record> extends AutoCloseable {
        int bulkSize();

        boolean bulkNext(RecordConsumer<Record> consumer);

        @Override
        void close();
    }

    Cursor<Record> getCursor();

    long storeSize();
}
