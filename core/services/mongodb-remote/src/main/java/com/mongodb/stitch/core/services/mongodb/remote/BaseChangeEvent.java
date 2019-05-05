/*
 * Copyright 2018-present MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mongodb.stitch.core.services.mongodb.remote;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bson.BsonDocument;

/**
 * Represents the set of properties that exist on all MongoDB change events produced
 * by watch streams in this SDK. Other change event types inherit from this type.
 *
 * @param <DocumentT> The type of the full document in the change event.
 */
public abstract class BaseChangeEvent<DocumentT> {
  @Nonnull private final OperationType operationType;
  @Nullable private final DocumentT fullDocument;
  @Nonnull private final BsonDocument documentKey;
  @Nullable private final UpdateDescription updateDescription;

  private final boolean hasUncommittedWrites;

  /**
   * Returns the operation type of the change that triggered the change event.
   *
   * @return the operation type of this change event.
   */
  @Nonnull
  public OperationType getOperationType() {
    return operationType;
  }

  /**
   * The full document at some point after the change has been applied.
   *
   * @return the full document.
   */
  @Nullable
  public DocumentT getFullDocument() {
    return fullDocument;
  }

  /**
   * The unique identifier for the document that was actually changed.
   *
   * @return the document key.
   */
  @Nonnull
  public BsonDocument getDocumentKey() {
    return documentKey;
  }

  /**
   * In the case of an update, the description of which fields have been added, removed or updated.
   *
   * @return the update description.
   */
  @Nullable
  public UpdateDescription getUpdateDescription() {
    return updateDescription;
  }

  /**
   * Indicates a local change event that has not yet been synchronized with a remote data store.
   * Used only for the sync use case.
   *
   * @return whether or not this change event represents uncommitted writes.
   */
  public boolean hasUncommittedWrites() {
    return hasUncommittedWrites;
  }

  BaseChangeEvent(
      @Nonnull final OperationType operationType,
      @Nullable final DocumentT fullDocument,
      @Nonnull final BsonDocument documentKey,
      @Nullable final UpdateDescription updateDescription,
      final boolean hasUncommittedWrites
  ) {
    this.operationType = operationType;
    this.fullDocument = fullDocument;
    this.documentKey = documentKey;
    this.updateDescription = updateDescription == null
        ? new UpdateDescription(null, null) : updateDescription;
    this.hasUncommittedWrites = hasUncommittedWrites;
  }

  /**
   * Converts the change event to a BSON representation, as it would look on a MongoDB change
   * stream.
   *
   * @return The BSON document representation of the change event.
   */
  public abstract BsonDocument toBsonDocument();
}
