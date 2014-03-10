package com.dslplatform.examples.android;

import java.io.IOException;

import org.slf4j.Logger;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.dslplatform.client.Bootstrap;
import com.dslplatform.examples.android.R;
import com.dslplatform.patterns.ServiceLocator;

public class MainActivity extends Activity {
	
	public static ServiceLocator locator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		try {
			locator = init();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    /**
	 * Call to initialize an instance of ServiceLocator with a dsl-project.ini
	 */
	public static ServiceLocator init() throws IOException {
		final ServiceLocator locator = Bootstrap.init(MainActivity.class
				.getResourceAsStream("/dsl-project.ini"));

		// an example how to resolve components from the ServiceLocator
		locator.resolve(Logger.class).info("Locator has been initialized.");
		return locator;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
