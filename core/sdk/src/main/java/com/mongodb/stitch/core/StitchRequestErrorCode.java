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

package com.mongodb.stitch.core;

/** StitchRequestErrorCode represents the reasons that a request may fail. */
public enum StitchRequestErrorCode {
  REQUEST_SIZE_ERROR("the request was too large to be processed by Stitch"),
  TRANSPORT_ERROR("the request transport encountered an error communicating with Stitch"),
  DECODING_ERROR("an error occurred while decoding a response from Stitch"),
  ENCODING_ERROR("an error occurred while encoding a request for Stitch"),
  BOOTSTRAP_ERROR("an error occurred retrieving application metadata from Stitch");

  private final String description;

  StitchRequestErrorCode(final String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return description;
  }
}
