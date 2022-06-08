package comp3350.team10.presentation;

import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import comp3350.team10.objects.*;
import comp3350.team10.R;

import java.util.LinkedList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private LinkedList<ListItem> localDataSet;          // the list Recyclerview renders
    private int selectedPos = RecyclerView.NO_POSITION; // tracks the last clicked item
    private FragToRecipeBook sendToRecipeBook;          // interface to pass data to recipebook
    private FragToMealDiary sendToMealDiary;            // interface to pass data to mealdiary
    private ListItem saved;                             // var to save a meal entry when we show context UI

    /**
     * getItemViewType
     * get the layout/fragment we want to use for a particular list item
     * required to show different layouts inside recyclerview
     * @param pos - position of data in the dataset
     * @return int - the numeric value of the Fragment type enum of the ListItem class
     */
    @Override
    public int getItemViewType(int pos) {
        return localDataSet.get(pos).getFragmentType().ordinal();
    }

    /**
     * TODO maybe we want a generic view inside every fragment frame layout so it's less awkward
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //private final FragmentContainerView fragmentView;
        //private final CardView fragmentView;
        private final FrameLayout fragmentView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            //fragmentView = (FragmentContainerView) view.findViewById(R.id.btnMealLog);
            //fragmentView = (CardView) view.findViewById(R.id.btnMealLog);
            fragmentView = (FrameLayout) view.findViewById(R.id.frame_container);

        }

        //public FragmentContainerView getFragmentView() {
        //public CardView getView() {
        public FrameLayout getView() {
            return fragmentView;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet LinkedList<ListItem> containing the data to populate views to be used
     *                by RecyclerView.
     */
    public RecyclerViewAdapter(LinkedList<ListItem> dataSet) {
        localDataSet = dataSet;
    }

    public void changeData(LinkedList<ListItem> newData) {
        localDataSet = newData;
        this.notifyDataSetChanged();
    }

    /**
     * Method where we assign the layout of a ViewHolder being created based on the Fragment Type enum
     * assigned to the ListItem object being "rendered"
     * @param viewGroup
     * @param viewType - the int returned by getItemViewType(int)
     * @return ViewHolder
     */
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view;
        ViewHolder viewHolder;

        switch (viewType) {
            case 0:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_log_card, viewGroup, false);
                break;
            case 1:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_modify_log, viewGroup, false);
                break;
            case 2:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_add_log, viewGroup, false);
                break;
            case 3: //creates cards
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_dbl_card, viewGroup, false);
                break;
            case 4:    //add thing to make it show this card/buttons thingy add enum
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_on_card_selection2, viewGroup, false);
                break;
            default:
                view = null;
                break;
        }
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /**
     * Here we set the data of a fragment being created and set onclick listeners based on the Fragment Type enum
     * assigned to the ListItem object being "rendered"
     * @param viewHolder
     * @param position
     */
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        //viewHolder.getFragmentView().setText(localDataSet[position]);
        //viewHolder.getView();
        //viewHolder.getView().setId(View.generateViewId());

        switch (viewHolder.getItemViewType()) {
            case 0:
                setDiaryEntryData(viewHolder, position);
                setDiaryEntryListeners(viewHolder);
                break;
            case 1:
                setDiaryContextListeners(viewHolder);
                break;
            case 2:
                setDiaryAddListeners(viewHolder);
                break;
            case 3:
                setRecipeData(viewHolder, position);    //this is what puts information into dbl cards
                setCardListeners(viewHolder, position);
                break;//viewHolder, position
            case 4:
                setCardSelectionListeners(viewHolder, position);
                break;
            default:
                //view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_add_log, viewGroup, false);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    //set the data of a meal entry fragment
    private void setDiaryEntryData(ViewHolder viewHolder, final int position){
        ((TextView) viewHolder.getView().findViewById(R.id.itemNameBox)).setText(((Edible) localDataSet.get(position)).getName()); //had two take two lines below out to make it work
        ((TextView) viewHolder.getView().findViewById(R.id.itemQtyBox)).setText(String.format("%3d", ((Edible) localDataSet.get(position)).getQuantity()));
        ((TextView) viewHolder.getView().findViewById(R.id.itemUnitBox)).setText(((Edible) localDataSet.get(position)).getBaseUnit().name());
        ((TextView) viewHolder.getView().findViewById(R.id.itemCalsBox)).setText(String.format("%3d", ((Edible) localDataSet.get(position)).getCalories()));
        ((ImageView) viewHolder.getView().findViewById(R.id.itemImage)).setImageResource(((Edible) localDataSet.get(position)).getIconPath());
    }

    //meal log card fragment click listeners
    private void setDiaryEntryListeners(ViewHolder viewHolder){
        viewHolder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPos = viewHolder.getAbsoluteAdapterPosition();
                //selectedPos = position;
                Context context = view.getContext();

                if (context != null) {
                    sendToMealDiary = (FragToMealDiary) context;
                    sendToMealDiary.showContextUI(selectedPos);
                }
            }
        });
    }

    //modify card type to be of type card select
    private void setCardListeners(ViewHolder viewHolder, int position) {
        viewHolder.getView().findViewById(R.id.cardView2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //localDataSet.get(position)
                Context context = view.getContext();

                System.out.println("...");
                if (context != null) {
                    sendToRecipeBook = (FragToRecipeBook) context;
                    sendToRecipeBook.showContextUI(position);
                }
            }
        });
    }

    //modify meal fragment click listeners
    private void setDiaryContextListeners(ViewHolder viewHolder){
        viewHolder.getView().findViewById(R.id.btnBackMealLog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPos = viewHolder.getAbsoluteAdapterPosition();
                Context context = view.getContext();

                if (context != null) {
                    sendToMealDiary = (FragToMealDiary) context;
                    sendToMealDiary.showContextUI(selectedPos);
                }
            }
        });
        viewHolder.getView().findViewById(R.id.btnDeleteMeal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPos = viewHolder.getAbsoluteAdapterPosition();
                Context context = view.getContext();

                if (context != null) {
                    sendToMealDiary = (FragToMealDiary) context;
                    sendToMealDiary.removeItem(selectedPos);
                }
            }
        });
        viewHolder.getView().findViewById(R.id.btnModifyMeal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPos = viewHolder.getAbsoluteAdapterPosition();
                Context context = view.getContext();

                if (context != null) {
                    sendToMealDiary = (FragToMealDiary) context;
                    sendToMealDiary.editItem(selectedPos);
                }
            }
        });
    }

    //add meal entry fragment click listener
    private void setDiaryAddListeners(ViewHolder viewHolder){
        viewHolder.getView().findViewById(R.id.btnAddMeal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPos = viewHolder.getAbsoluteAdapterPosition();
                Context context = view.getContext();

                if (context != null) {
                    sendToMealDiary = (FragToMealDiary) context;
                    sendToMealDiary.addEntry(selectedPos);
                }
            }
        });
    }

    private void setRecipeData(ViewHolder viewHolder, int position){
        ((ImageView) viewHolder.getView().findViewById(R.id.mealImage)).setImageResource(((Edible)((RecipeBookItem) localDataSet.get(position)).getFood()).getIconPath());
        ((TextView) viewHolder.getView().findViewById(R.id.mealDesc)).setText(((Edible)((RecipeBookItem) localDataSet.get(position)).getFood()).getName());
        ((TextView) viewHolder.getView().findViewById(R.id.mealCals)).setText(Integer.toString(((Edible)((RecipeBookItem) localDataSet.get(position)).getFood()).getCalories()));
    }
    private void setCardSelectionListeners(ViewHolder viewHolder, int position) {
        Button viewButton = (Button)viewHolder.getView().findViewById(R.id.viewBtn);
        Button addButton = (Button)viewHolder.getView().findViewById(R.id.addToPlannerBtn);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //System.out.println("adding?????");
                Context context = view.getContext();

                if (context != null) {
                    sendToRecipeBook = (FragToRecipeBook) context;
                    sendToRecipeBook.addFoodEntry(position);
                    //parentComm.addDiaryItem((DiaryItem)localDataSet.get(position));
                }
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //need to pass tab information, and edible
                Intent intent = new Intent(view.getContext(), ActivityViewEdible.class);
                String currActivityName = view.getContext().getClass().getName();

                intent.putExtra(ActivityViewEdible.RETURN_ACTIVITY_NAME, currActivityName);
                //intent.putExtra("edibleItem", (Parcelable)localDataSet.get(position));

                view.getContext().startActivity(intent);
            }
        });
    }
}
