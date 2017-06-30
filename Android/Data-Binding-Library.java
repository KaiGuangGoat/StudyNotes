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


Import

	import just like java :

	<data>
		<import type="android.view.View"/>
	</data>
	<TextView
		android:text="@{user.lastName}"
		android:visibility="@{user.isAdult ? View.VISIBLE : View.GONE}"
	></TextView>

	Or like this

	<data>
	    <import type="com.example.MyStringUtils"/>
	    <variable name="user" type="com.example.User"/>
	</data>
…
	<TextView
		android:text="@{MyStringUtils.capitalize(user.lastName)}"
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"/>

Variables

	<data>
	    <import type="android.graphics.drawable.Drawable"/>
	    <variable name="user"  type="com.example.User"/>
	    <variable name="image" type="Drawable"/>
	    <variable name="note"  type="String"/>
	</data>

Custom Binding Class Names

	default ---> My moduule package + databinding + layoutNameBinding
		eg. com.sample.myapp.databinding.MainActivityBinding

	custom 
			---><data class="ContactItem">
				    ...
				</data>

				<data class=".ContactItem">
				    ...
				</data>

				<data class="com.example.ContactItem">
				    ...
				</data>

&lt;  &gt; --> <>


Data Objects
	
	POJO may be used for data binding ,but will not cause the UI updata;

	Use
		---> Observable objects, observable fields, and observable collections.

		Observable Objects

			-->
				private static class User extends BaseObservable {
				   private String firstName;
				   private String lastName;
				   @Bindable
				   public String getFirstName() {
				       return this.firstName;
				   }
				   @Bindable
				   public String getLastName() {
				       return this.lastName;
				   }
				   public void setFirstName(String firstName) {
				       this.firstName = firstName;
				       notifyPropertyChanged(BR.firstName);
				   }
				   public void setLastName(String lastName) {
				       this.lastName = lastName;
				       notifyPropertyChanged(BR.lastName);
				   }
				}

				BR generated 

		Observable Fields

			-->
				private static class User {
				   public final ObservableField<String> firstName =
				       new ObservableField<>();
				   public final ObservableField<String> lastName =
				       new ObservableField<>();
				   public final ObservableInt age = new ObservableInt();
				}

		Observable Collections

			-->
				ObservableArrayMap<String, Object> user = new ObservableArrayMap<>();
				user.put("firstName", "Google");
				user.put("lastName", "Inc.");
				user.put("age", 17);


