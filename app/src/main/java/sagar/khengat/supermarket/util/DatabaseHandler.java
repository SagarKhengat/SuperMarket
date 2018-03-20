
package sagar.khengat.supermarket.util;

import android.content.Context;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sagar.khengat.supermarket.R;
import sagar.khengat.supermarket.model.Cart;
import sagar.khengat.supermarket.model.Customer;
import sagar.khengat.supermarket.model.Gender;
import sagar.khengat.supermarket.model.Product;


public class DatabaseHandler {
	private static final String TAG = "DatabaseHandler";

	RuntimeExceptionDao<Gender, Integer> genderDao;


	private RuntimeExceptionDao<Customer, Integer> customerDao;
	private RuntimeExceptionDao<Product, Integer> productDao;
	private RuntimeExceptionDao<Cart, Integer> cartDao;




	private DatabaseHelper databaseHelper;

	private Context context;

	public DatabaseHandler() {

	}

	public DatabaseHandler(Context context) {
		this.context = context;
		initElements();
	}

	public void initElements() {


		genderDao = getHelper().getGenderDao();



		customerDao = getHelper().getCustomerDao();

		productDao = getHelper().getProductDao();
		cartDao = getHelper().getCartDao();







	}




	private DatabaseHelper getHelper() {
		databaseHelper = null;
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(context,
					DatabaseHelper.class);
		}
		return databaseHelper;
	}

	/**
	 * This method to check user exist or not
	 *
	 * @param email
	 * @return true/false
	 */
	public boolean checkCustomer(String email) {
		boolean b = false;
		List<Customer> mListAllStores = fnGetAllCustomer();
		try {
			QueryBuilder < Customer, Integer> qb = customerDao.queryBuilder();

			for (Customer user:
					mListAllStores) {

				if (user.getId().equals(email) || user.getName().equals(email))
				{
					b = true;
				}
				else
				{

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return b;
	}

	public boolean checkCustomerForRegister(String email) {
		boolean b = false;
		List<Customer> mListAllStores = fnGetAllCustomer();
		try {
			QueryBuilder < Customer, Integer> qb = customerDao.queryBuilder();

			for (Customer user:
					mListAllStores) {

				if (user.getId().equals(email))
				{
					b = true;
				}
				else
				{

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return b;
	}



	/**
	 * This method to check user exist or not
	 *
	 * @param email
	 * @return true/false
	 */






	/**
	 * This method to check user exist or not
	 *
	 *
	 */

	public boolean checkProduct(String username) {

		boolean b = false;
		List<Product> mListAllStores = fnGetAllProduct();
		try {
			QueryBuilder < Product, Integer> qb = productDao.queryBuilder();

			for (Product user:
					mListAllStores) {

				if (user.getProductName().equals(username))
				{
					b = true;
				}
				else
				{

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return b;
	}

	public boolean checkGender(Gender username) {

		boolean b = false;
		List<Gender> mListAllStores = fnGetAllGender();
		try {
			QueryBuilder < Gender, Integer> qb = genderDao.queryBuilder();

			for (Gender user:
					mListAllStores) {

				if (user.getGenderName().equals(username.getGenderName()))
				{
					b = true;
				}
				else
				{

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return b;
	}


	/**
	 * This method to check user exist or not
	 *
	 * @param email
	 * @param password
	 * @return true/false
	 */
	public boolean checkCustomer(String email, String password) {

		boolean b = false;
		List<Customer> mListAllStores = fnGetAllCustomer();
		try {
			QueryBuilder < Customer, Integer> qb = customerDao.queryBuilder();

			for (Customer user:
					mListAllStores) {

				if ((user.getName().equals(email) && user.getPassword().equals(password)) ||
						((user.getId().equals(email) && user.getPassword().equals(password))))
				{
					b = true;
				}
				else
				{

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return b;
	}

	public boolean checkCustomerForUpdatePassword(String email, String password) {

		boolean b = false;
		List<Customer> mListAllStores = fnGetAllCustomer();
		try {
			QueryBuilder < Customer, Integer> qb = customerDao.queryBuilder();

			for (Customer user:
					mListAllStores) {

				if (user.getName().equals(email) && user.getContactNo().equals(password))
				{
					b = true;
				}
				else
				{

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return b;
	}



	public void addCustomer(Customer user) {
		try
		{
			customerDao.create( user );
			Toast.makeText(context, context.getString(R.string.success_message), Toast.LENGTH_LONG).show();

		} catch(OutOfMemoryError e) {
			e.printStackTrace();
			Toast.makeText( context, "Problem in memory allocation. Please free some memory space and try again.", Toast.LENGTH_LONG ).show();
		} catch(Exception e) {
			Toast.makeText( context, "Problem in adding User. Please try again.", Toast.LENGTH_LONG ).show();

			e.printStackTrace();
		}
	}


	public boolean addGender(Gender user) {
		boolean b = false;
		try
		{
			genderDao.create( user );
			b = true;

		} catch(OutOfMemoryError e) {
			e.printStackTrace();
			Toast.makeText( context, "Problem in memory allocation. Please free some memory space and try again.", Toast.LENGTH_LONG ).show();
		} catch(Exception e) {
			Toast.makeText( context, "Problem in adding User. Please try again.", Toast.LENGTH_LONG ).show();

			e.printStackTrace();
		}
		return  b;
	}





	public List<Customer> fnGetAllCustomer() {
		List< Customer > mListIndustry = new ArrayList<>();

		try {
			QueryBuilder< Customer, Integer> queryBuilder = customerDao.queryBuilder();
			PreparedQuery< Customer > preparedQuery = null;
			preparedQuery = queryBuilder.prepare();
			mListIndustry = customerDao.query( preparedQuery );
		} catch ( SQLException e ) {
			e.printStackTrace();
		} catch(OutOfMemoryError e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}

		return mListIndustry;
	}






	public List<Product> fnGetAllProduct() {
		List< Product > mListIndustry = new ArrayList<>();

		try {
			QueryBuilder< Product, Integer> queryBuilder = productDao.queryBuilder();
			PreparedQuery< Product > preparedQuery = null;
			preparedQuery = queryBuilder.prepare();
			mListIndustry = productDao.query( preparedQuery );
		} catch ( SQLException e ) {
			e.printStackTrace();
		} catch(OutOfMemoryError e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}

		return mListIndustry;
	}



	public List<Cart> fnGetAllCart() {
		List< Cart > mListIndustry = new ArrayList<>();

		try {
			QueryBuilder< Cart, Integer> queryBuilder = cartDao.queryBuilder();
			PreparedQuery< Cart > preparedQuery = null;
			preparedQuery = queryBuilder.prepare();
			mListIndustry = cartDao.query( preparedQuery );
		} catch ( SQLException e ) {
			e.printStackTrace();
		} catch(OutOfMemoryError e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}

		return mListIndustry;
	}



	public List<Gender> fnGetAllGender() {
		List< Gender > mListIndustry = new ArrayList<>();

		try {
			QueryBuilder< Gender, Integer> queryBuilder = genderDao.queryBuilder();
			PreparedQuery< Gender > preparedQuery = null;
			preparedQuery = queryBuilder.prepare();
			mListIndustry = genderDao.query( preparedQuery );
		} catch ( SQLException e ) {
			e.printStackTrace();
		} catch(OutOfMemoryError e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mListIndustry;
	}






	public void addProduct(Product product) {
		try
		{
			productDao.create( product );
		} catch(OutOfMemoryError e) {
			e.printStackTrace();
			Toast.makeText( context, "Problem in memory allocation. Please free some memory space and try again.", Toast.LENGTH_LONG ).show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void addToCart(Cart product) {
		try
		{
			cartDao.create( product );
			Toast.makeText( context, "Product Added in Cart Successfully.", Toast.LENGTH_LONG ).show();
		} catch(OutOfMemoryError e) {
			e.printStackTrace();
			Toast.makeText( context, "Problem in memory allocation. Please free some memory space and try again.", Toast.LENGTH_LONG ).show();
		} catch(Exception e) {
			Toast.makeText( context, "Something went wrong. Please try again.", Toast.LENGTH_LONG ).show();
			e.printStackTrace();
		}
	}


	public int fnGetCartCount(Customer store)
	{
		int i = 0;


		List<Cart> mListStores = new ArrayList<>();
		List<Cart> mListAllStores = fnGetAllCart();

		try {
//			QueryBuilder < Store, Integer > qb = storeDao.queryBuilder();
//			Where<Store, Integer> where = qb.where();
//
//			where.like( "areaId", area.getAreaId() );//.or().like("customerPrintAs", "%"+nameToSearch+"%");
//
//
//
//			// It filters only data present in DB fetched at the time of sync.
//			PreparedQuery < Store> pq = where.prepare();
//			mListStores = storeDao.query( pq );


			for (Cart cart : mListAllStores)
			{
				if(cart.getCustomer().getId().equals(store.getId()))
				{
					mListStores.add(cart);
				}
			}

			if(mListStores.size()!=0) {
				i = mListStores.size();
			}
		} catch (Exception e) {

			e.printStackTrace();
		}


		return i;
	}


	public List<Cart> fnGetAllCart(Customer store)
	{

		List<Cart> mListStores = new ArrayList<>();
		List<Cart> mListAllStores = fnGetAllCart();

		try {
//			QueryBuilder < Store, Integer > qb = storeDao.queryBuilder();
//			Where<Store, Integer> where = qb.where();
//
//			where.like( "areaId", area.getAreaId() );//.or().like("customerPrintAs", "%"+nameToSearch+"%");
//
//
//
//			// It filters only data present in DB fetched at the time of sync.
//			PreparedQuery < Store> pq = where.prepare();
//			mListStores = storeDao.query( pq );


			for (Cart cart : mListAllStores)
			{
				if(cart.getCustomer().getId().equals(store.getId()))
				{
					mListStores.add(cart);
				}
			}


		} catch (Exception e) {

			e.printStackTrace();
		}


		return mListStores;
	}



	public void deleteCartitem(Cart cart) {
		try
		{
			cartDao.delete(cart);
			Toast.makeText(context, "Product Deleted Successfully", Toast.LENGTH_LONG).show();

		}
		catch(OutOfMemoryError e)
		{
			e.printStackTrace();
			Toast.makeText(context, "Problem in memory allocation. Please free some memory space and try again.", Toast.LENGTH_LONG).show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	public void fnDeleteAllCart(Customer store)
	{

		List<Cart> mListStores = new ArrayList<>();
		List<Cart> mListAllStores = fnGetAllCart();

		try {
//			QueryBuilder < Store, Integer > qb = storeDao.queryBuilder();
//			Where<Store, Integer> where = qb.where();
//
//			where.like( "areaId", area.getAreaId() );//.or().like("customerPrintAs", "%"+nameToSearch+"%");
//
//
//
//			// It filters only data present in DB fetched at the time of sync.
//			PreparedQuery < Store> pq = where.prepare();
//			mListStores = storeDao.query( pq );


			for (Cart cart : mListAllStores)
			{
				if(cart.getCustomer().getId().equals(store.getId()))
				{
					cartDao.delete(cart);
				}
			}


		} catch (Exception e) {

			e.printStackTrace();
		}



	}





	public Product fnGetProductFromCart(Cart cart)
	{



		List<Product> mListAllStores = fnGetAllProduct();

		try {
//			QueryBuilder < Store, Integer > qb = storeDao.queryBuilder();
//			Where<Store, Integer> where = qb.where();
//
//			where.like( "areaId", area.getAreaId() );//.or().like("customerPrintAs", "%"+nameToSearch+"%");
//
//
//
//			// It filters only data present in DB fetched at the time of sync.
//			PreparedQuery < Store> pq = where.prepare();
//			mListStores = storeDao.query( pq );


			for (Product product : mListAllStores)
			{
//					if(product.getProductId() == cart.getProductCartId())
//					{
//						return product;
//					}
			}


		} catch (Exception e) {

			e.printStackTrace();
		}


		return null;
	}


//	public Cart fnGetCartFromCartHistory(History cart)
//	{
//
//
//
//		List <Cart> mListAllStores = fnGetAllCart();
//
//		try {
////			QueryBuilder < Store, Integer > qb = storeDao.queryBuilder();
////			Where<Store, Integer> where = qb.where();
////
////			where.like( "areaId", area.getAreaId() );//.or().like("customerPrintAs", "%"+nameToSearch+"%");
////
////
////
////			// It filters only data present in DB fetched at the time of sync.
////			PreparedQuery < Store> pq = where.prepare();
////			mListStores = storeDao.query( pq );
//
//
//			for (Cart product : mListAllStores)
//			{
//				if(product.getProductId().equals(cart.getProductId()))
//				{
//					return product;
//				}
//			}
//
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//
//
//		return null;
//	}


	public void updateCustomer(Customer customer)
	{
		try
		{
			UpdateBuilder<Customer, Integer> updateBuilder = customerDao.updateBuilder();
			updateBuilder.where().eq("id",customer.getId());
			updateBuilder.updateColumnValue("name",customer.getName());
			updateBuilder.updateColumnValue("contactNo",customer.getContactNo());
			updateBuilder.updateColumnValue("address",customer.getAddress());
			updateBuilder.update();
			Toast.makeText( context, "Profile changed Successfully...", Toast.LENGTH_LONG ).show();
		} catch(OutOfMemoryError e)
		{
			e.printStackTrace();
			Toast.makeText( context, "Problem in updating Profile try again.", Toast.LENGTH_LONG ).show();

		}
		catch(Exception e)
		{
			e.printStackTrace();
			Toast.makeText( context, "Problem in updating profile try again.", Toast.LENGTH_LONG ).show();

		}
	}





	public Customer getCustomer(String retailerName, String password)
	{
		Customer b = null;
		List<Customer> mListAllStores = fnGetAllCustomer();
		try {
			QueryBuilder < Customer, Integer> qb = customerDao.queryBuilder();

			for (Customer user:
					mListAllStores) {

				if ((user.getName().equals(retailerName) && user.getPassword().equals(password)) ||
						((user.getId().equals(retailerName) && user.getPassword().equals(password))))
				{
					b = user;
				}
				else
				{

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return b;
	}





//	public void updateProduct(Product product)
//	{
//
//		try
//		{
//			UpdateBuilder<Product, Integer> deleteBuilder = productDao.updateBuilder();
//			deleteBuilder.updateColumnValue("productName", product.getProductName());
//			deleteBuilder.updateColumnValue("productSize", product.getProductSize());
//			deleteBuilder.updateColumnValue("productOriginalPrice", product.getProductOriginalPrice());
//			deleteBuilder.updateColumnValue("productGstPrice", product.getProductGstPrice());
//			deleteBuilder.updateColumnValue("productUnit", product.getProductUnit());
//
//			deleteBuilder.where().eq("productId", product.getProductId());
//			deleteBuilder.update();
//
//
//		}
//		catch(OutOfMemoryError e)
//		{
//			e.printStackTrace();
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//	}

	public void deleteProduct(Product product)
	{
		try {
			DeleteBuilder<Product, Integer> deleteBuilder = productDao
					.deleteBuilder();
			deleteBuilder.where().eq("productId", product.getProductId());
			deleteBuilder.delete();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}



}