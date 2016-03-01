/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.cloud.stream.binding;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.cloud.stream.binder.Binding;

/**
 * A {@link BindableAdapter} that stores the dynamic destination names and handle their unbinding.
 *
 * @author Ilayaperumal Gopinathan
 */
public final class DynamicBindable extends BindableAdapter {

	/**
	 * Map containing dynamic destination names and their bindings.
	 */
	private Map<String, Binding> outputBindings = new HashMap<>();

	void addDynamicOutputs(String name, Binding binding) {
		this.outputBindings.put(name, binding);
	}

	@Override
	public Set<String> getOutputs() {
		return Collections.unmodifiableSet(outputBindings.keySet());
	}

	@Override
	public void unbindOutputs(ChannelBindingService adapter) {
		for (Map.Entry<String, Binding> entry: outputBindings.entrySet()) {
			entry.getValue().unbind();
		}
	}
}
