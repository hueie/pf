package com.polarisfinder;

import org.springframework.context.annotation.Configuration;

@Configuration
public class LibLoading {
	static {
		//System.setProperty("java.library.path", "C:\\Users\\Kait\\git\\polarisfinder\\polarisfinder\\libs");
		//System.loadLibrary( "opencv_java320.dll" );    
		//System.load( "C:\\Users\\Kait\\git\\polarisfinder\\polarisfinder\\libs\\opencv_java320.dll" );
	}
}
