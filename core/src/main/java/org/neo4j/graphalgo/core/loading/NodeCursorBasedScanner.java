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

import org.neo4j.graphalgo.compat.Neo4jProxy;
import org.neo4j.graphalgo.core.SecureTransaction;
import org.neo4j.internal.kernel.api.NodeCursor;
import org.neo4j.internal.kernel.api.Scan;
import org.neo4j.kernel.api.KernelTransaction;
import org.neo4j.kernel.impl.store.NeoStores;
import org.neo4j.kernel.impl.store.NodeStore;

final class NodeCursorBasedScanner extends AbstractCursorBasedScanner<NodeReference, NodeCursor, NodeStore> {

    static final StoreScanner.Factory<NodeReference> FACTORY = NodeCursorBasedScanner::new;

    private NodeCursorBasedScanner(int prefetchSize, SecureTransaction transaction) {
        super(prefetchSize, transaction);
    }

    @Override
    NodeStore store(NeoStores neoStores) {
        return neoStores.getNodeStore();
    }

    @Override
    NodeCursor entityCursor(KernelTransaction transaction) {
        return Neo4jProxy.allocateNodeCursor(transaction.cursors(), transaction.pageCursorTracer());
    }

    @Override
    Scan<NodeCursor> entityCursorScan(KernelTransaction transaction) {
        return transaction.dataRead().allNodesScan();
    }

    @Override
    NodeReference cursorReference(NodeCursor cursor) {
        return new NodeCursorReference(cursor);
    }
}
