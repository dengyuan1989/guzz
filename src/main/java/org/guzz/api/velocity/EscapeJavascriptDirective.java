/*
 * Copyright 2008-2012 the original author or authors.
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
 *
 */
package org.guzz.api.velocity;

import java.io.IOException;
import java.io.Writer;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.guzz.util.StringUtil;

/**
 * 
 * 在输入html代码的同时，转义js代码。
 * 
 * @author liukaixuan(liukaixuan@gmail.com)
 */
public class EscapeJavascriptDirective extends Directive {

	public int getType() {
		return LINE ;
	}

	public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
		if(node.jjtGetNumChildren() != 1){
			throw new RuntimeException(getName() + " only and must accept one parameter!") ;
		}
		
		Object param2 = node.jjtGetChild(0).value(context) ;
		
		//如果为null，则什么都不输出。
		if(param2 != null){
			String param = String.valueOf(param2) ;
			param = StringUtil.replaceStringIgnoreCase(param, "<script", "< script") ;
			param = StringUtil.replaceStringIgnoreCase(param, "</script", "</ script") ;
			
			writer.append(param) ;
		}
        
        return true;
	}

	public String getName() {
		return "escapeJS" ;
	}
}
