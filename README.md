# WallMart_Coding_Chalange
Coding Chalange

Chalange:
Create a new application with WalmartLabs Android Test API integrated (https://developer.walmartlabs.com/io-docs). The application
should have two screens

Approach:

Architecture:
MVP

Librairies Used:
RetroFit (Network Calls)
ButterKnife (View Binding)
RecyclerView (Scrolling View)
CardView (UX)
Design Library (UX)
Mockito (Testing)
Glide (Image Loading and Caching)
Dagger (Dependency Injection)

Steps:
1. Create a Retrofit Helper Class To make a call to the Api to aquire a list of items based off
    of a keyword*
2. Once the call returns it passes the created list to the Recycler View Adaptor to Fill a list
    of items related to the keyword.
3. Once the list has loaded te user May either scroll down the list or click on an item to recive
    more detailed information.
4 Should the end of the list be reached another call will be made to the Api to aquire the next
    set of items related to the keyword specified then be added to the current list**



*The inital search has a keyword specified but after the initial load the user may search for any
    keyword entered
** This will Happen up till 100 items are in the list at that point the list will not continue.


Application:

Activities:
ItemList Activity: Contains a search bar to allow the user to search for a diffrent set of items.
ItemDetails Activity: Contains more detailed Information about the selected item and another
    Recycler View to scroll thru the list of items.

Utilities:
ItemListAdapter: RecyclerView Adaptor for the ItemListActivity
ItemDetailesAdapter: RecyclerView Adaptor for the ItemDetailesActivity
RetrofitHelper: Retrofit factory class and interface for network calls.



