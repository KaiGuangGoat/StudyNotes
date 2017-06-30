It is a support library,can use it back to version 2.1

need 
	-->Gradle 1.5.0-alpha1 ↑
	-->AndroidStudio 1.3 or latter


Buile Environment

	android {
	    ....
	    dataBinding {
	        enabled = true
	    }
	}


Data Binding Layout File

	<layout>
		<data>
			<variable name="user" type="com.example.User"/>
		</data>


		<LinearLayout>
			<TextView android:layout_width="wrap_content"
	           android:layout_height="wrap_content"
	           android:text="@{user.firstName}"/>
	       <TextView android:layout_width="wrap_content"
	           android:layout_height="wrap_content"
	           android:text="@{user.lastName}"/>
		</LinearLayout>
	</layout>

Data Object

	class User{
		String firstName;
		String lastName;
		getter();
		setter();
	}

	@{user.firstName} --> user.getFirstName();

Binding Data 
	
	class MainActivity{
		onCreate(){
			MainActivityBinding binding = MainActivityBinding.setContentView(this,R.layout.main_activity);
			User user = new User(firstName,lastName);
			binding.setUser(user);
		}
	}