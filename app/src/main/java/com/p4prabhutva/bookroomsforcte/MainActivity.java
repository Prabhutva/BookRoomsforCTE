package com.p4prabhutva.bookroomsforcte;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


	TextView timefrom, timeto, dateon, room;
	MaterialButton sendbutton, editbutton;
	final Context c = this;
	int hourfrom, hourto, minfrom, minto;
	int date;
	int month;
	SharedPreferences sharedPreferences;
	String dateSelected, timefromSelected, timetoSelected, roomno;
	String mailSubject;
	String mailSend;
	String signature;
	String mailText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sharedPreferences = getApplicationContext().getSharedPreferences("myPref", MODE_PRIVATE);
		final SharedPreferences.Editor editor = sharedPreferences.edit();

		mailSubject=sharedPreferences.getString("subject","Room booking for CTE");
		mailSend=sharedPreferences.getString("receiver","instruction.office@goa.bits-pilani.ac.in");
		signature=sharedPreferences.getString("signature","Regards,\nChinmay Gokhale,\nVice President, cte\nBirla Institute of Technology and Science, Pilani\nK.K. Birla Goa Campus\n9619681383");
		mailText="Respected Ma'am,\nPlease book " + roomno + " on " + dateSelected + " from " + timefromSelected + " to " + timetoSelected + " for CTE Classes.\n\n"+signature;

		timefrom = findViewById(R.id.from);
		timeto = findViewById(R.id.to);
		dateon = findViewById(R.id.ondate);
		room = findViewById(R.id.room);
		sendbutton = findViewById(R.id.sendbutton);
		editbutton = findViewById(R.id.editbutton);


		room.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
				View mView = layoutInflaterAndroid.inflate(R.layout.roomdialogbox, null);
				AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
				alertDialogBuilderUserInput.setView(mView);
				final EditText roomet = mView.findViewById(R.id.selectRoomET);

				alertDialogBuilderUserInput
						.setCancelable(true)
						.setPositiveButton("Select", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialogBox, int id) {
								// ToDo get user input here
								roomno = roomet.getText().toString();
								room.setText(roomno);
							}
						})

						.setNegativeButton("Cancel",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialogBox, int id) {
										dialogBox.cancel();
									}
								});

				AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
				alertDialogAndroid.show();
			}
		});

		timefrom.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
				View mView = layoutInflaterAndroid.inflate(R.layout.fromdialogbox, null);
				AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
				alertDialogBuilderUserInput.setView(mView);
				final TimePicker timePickerFrom = mView.findViewById(R.id.tpfrom);
				timePickerFrom.setMinute(0);
				alertDialogBuilderUserInput
						.setCancelable(true)
						.setPositiveButton("Select", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialogBox, int id) {
								// ToDo get user input here
								hourfrom = timePickerFrom.getHour();
								minfrom = timePickerFrom.getMinute();
								Calendar calendar = Calendar.getInstance();
								calendar.set(Calendar.HOUR_OF_DAY, hourfrom);
								calendar.set(Calendar.MINUTE, minfrom);
								SimpleDateFormat format = new SimpleDateFormat("h:mm a");
								timefromSelected = format.format(calendar.getTime());
								timefrom.setText(timefromSelected);
							}
						})

						.setNegativeButton("Cancel",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialogBox, int id) {
										dialogBox.cancel();
									}
								});

				AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
				alertDialogAndroid.show();
			}
		});
		timeto.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
				View mView = layoutInflaterAndroid.inflate(R.layout.todialogbox, null);
				AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
				alertDialogBuilderUserInput.setView(mView);
				final TimePicker timePickerTo = mView.findViewById(R.id.tpto);
				timePickerTo.setMinute(0);
				alertDialogBuilderUserInput
						.setCancelable(true)
						.setPositiveButton("Select", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialogBox, int id) {
								// ToDo get user input here

								hourto = timePickerTo.getHour();
								minto = timePickerTo.getMinute();
								Calendar calendar = Calendar.getInstance();
								calendar.set(Calendar.HOUR_OF_DAY, hourto);
								calendar.set(Calendar.MINUTE, minto);
								SimpleDateFormat format = new SimpleDateFormat("h:mm a");
								timetoSelected = format.format(calendar.getTime());
								timeto.setText(timetoSelected);
							}
						})

						.setNegativeButton("Cancel",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialogBox, int id) {
										dialogBox.cancel();
									}
								});

				AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
				alertDialogAndroid.show();
			}
		});
		dateon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
				View mView = layoutInflaterAndroid.inflate(R.layout.datedialogbox, null);
				AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
				alertDialogBuilderUserInput.setView(mView);
				final DatePicker datePicker = mView.findViewById(R.id.date);

				datePicker.setMinDate(Calendar.getInstance().getTimeInMillis());


				alertDialogBuilderUserInput
						.setCancelable(true)
						.setPositiveButton("Select", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialogBox, int id) {
								// ToDo get user input here

								date = datePicker.getDayOfMonth();
								month = datePicker.getMonth();
								Calendar calendar = Calendar.getInstance();
								calendar.set(Calendar.DATE, date);
								calendar.set(Calendar.MONTH, month);
								SimpleDateFormat format = new SimpleDateFormat("d MMMM");
								dateSelected = format.format(calendar.getTime());
								dateon.setText(dateSelected);
							}
						})

						.setNegativeButton("Cancel",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialogBox, int id) {
										dialogBox.cancel();
									}
								});

				AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
				alertDialogAndroid.show();
			}
		});



		sendbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mailSubject=sharedPreferences.getString("subject","Room booking for CTE");
				mailSend=sharedPreferences.getString("receiver","instruction.office@goa.bits-pilani.ac.in");
				signature=sharedPreferences.getString("signature","Regards,\nChinmay Gokhale,\nVice President, cte\nBirla Institute of Technology and Science, Pilani\nK.K. Birla Goa Campus\n9619681383");
				mailText="Respected Ma'am,\nPlease book " + roomno + " on " + dateSelected + " from " + timefromSelected + " to " + timetoSelected + " for CTE Classes.\n\n"+signature;
				/*Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
						"mailto", mailSend, null));
				emailIntent.putExtra(Intent.EXTRA_SUBJECT, mailSubject);
				emailIntent.putExtra(Intent.EXTRA_TEXT, mailText);
				c.startActivity(Intent.createChooser(emailIntent, null));*/

				LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
				View mView = layoutInflaterAndroid.inflate(R.layout.senddialogbox, null);
				AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
				alertDialogBuilderUserInput.setView(mView);
				final TextView receiver = mView.findViewById(R.id.sendReceiver);
				final TextView subject = mView.findViewById(R.id.sendSubject);
				final TextView text = mView.findViewById(R.id.sendText);
				final EditText ret = mView.findViewById(R.id.sendReceiverET);
				final EditText set = mView.findViewById(R.id.sendSubjectET);
				final EditText tet = mView.findViewById(R.id.sendTextET);

				//fill everything with data
				receiver.setText(mailSend);
				ret.setText(mailSend);
				subject.setText(mailSubject);
				set.setText(mailSubject);
				text.setText(mailText);
				tet.setText(mailText);

				alertDialogBuilderUserInput
						.setCancelable(true)
						.setPositiveButton("Send", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialogBox, int id) {
								// ToDo get user input here
								//send mail
								Log.i("Send email", "");

								/*Intent emailIntent = new Intent(Intent.ACTION_SEND);

								emailIntent.setData(Uri.parse("mailto:"));
								emailIntent.setType("text/plain");
								emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
								emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

								try {
									startActivity(Intent.createChooser(emailIntent, "Send mail..."));
									finish();
									Log.i("Mail sent", "");
								} catch (android.content.ActivityNotFoundException ex) {
									Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
								}*/

								editor.putString("subject",mailSubject).putString("receiver",mailSend).commit();
								Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
										"mailto", mailSend, null));
								emailIntent.putExtra(Intent.EXTRA_SUBJECT, mailSubject);
								emailIntent.putExtra(Intent.EXTRA_TEXT, mailText);
								c.startActivity(Intent.createChooser(emailIntent, null));

								/*new Thread(new Runnable() {

									public void run() {

										try {

											GMailSender sender = new GMailSender(

													"f20180115@goa.bits-pilani.ac.in",

													"import java.prabhutva.bits;");

											sender.sendMail("Test mail", "This mail has been sent from android app along with attachment",

													"f20180115@goa.bits-pilani.ac.in",

													"p4prabhutva@gmail.com");


										} catch (Exception e) {

											Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();


										}

									}

								}).start();*/
							}

						})
						.setNeutralButton("Edit", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								//edit data

								LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
								View mView = layoutInflaterAndroid.inflate(R.layout.senddialogbox, null);
								AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
								alertDialogBuilderUserInput.setView(mView);
								final TextView receiver = mView.findViewById(R.id.sendReceiver);
								final TextView subject = mView.findViewById(R.id.sendSubject);
								final TextView text = mView.findViewById(R.id.sendText);
								final EditText ret = mView.findViewById(R.id.sendReceiverET);
								final EditText set = mView.findViewById(R.id.sendSubjectET);
								final EditText tet = mView.findViewById(R.id.sendTextET);

								//fill everything with data
								receiver.setText(mailSend);
								ret.setText(mailSend);
								subject.setText(mailSubject);
								set.setText(mailSubject);
								text.setText(mailText);
								tet.setText(mailText);

								receiver.setVisibility(View.GONE);
								text.setVisibility(View.GONE);
								subject.setVisibility(View.GONE);
								ret.setVisibility(View.VISIBLE);
								set.setVisibility(View.VISIBLE);
								tet.setVisibility(View.VISIBLE);
								alertDialogBuilderUserInput
										.setCancelable(true)
										.setPositiveButton("Send", new DialogInterface.OnClickListener() {
											public void onClick(DialogInterface dialogBox, int id) {
												// ToDo get user input here
												//send mail
												mailSend=ret.getText().toString();
												mailSubject=set.getText().toString();
												mailText=tet.getText().toString();
												editor.putString("subject",mailSubject).putString("receiver",mailSend).commit();


												Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
														"mailto", mailSend, null));
												emailIntent.putExtra(Intent.EXTRA_SUBJECT, mailSubject);
												emailIntent.putExtra(Intent.EXTRA_TEXT, mailText);
												c.startActivity(Intent.createChooser(emailIntent, null));
											}
										})
										.setNegativeButton("Cancel",
												new DialogInterface.OnClickListener() {
													public void onClick(DialogInterface dialogBox, int id) {
														dialogBox.cancel();
													}
												});

								AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
								alertDialogAndroid.show();
							}
						})
						.setNegativeButton("Cancel",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialogBox, int id) {
										dialogBox.cancel();
									}
								});

				AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
				alertDialogAndroid.show();
			}
		});

		editbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mailSubject="Room booking for CTE Classes";
				mailSend="instruction.office@goa.bits-pilani.ac.in";
				signature="Regards,\nChinmay Gokhale,\nVice President, cte\nBirla Institute of Technology and Science, Pilani\nK.K. Birla Goa Campus\n9619681383";

				LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
				View mView = layoutInflaterAndroid.inflate(R.layout.senddialogbox, null);
				AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
				alertDialogBuilderUserInput.setView(mView);

				final TextView receiver = mView.findViewById(R.id.sendReceiver);
				final TextView subject = mView.findViewById(R.id.sendSubject);
				final TextView text = mView.findViewById(R.id.sendText);
				final EditText ret = mView.findViewById(R.id.sendReceiverET);
				final EditText set = mView.findViewById(R.id.sendSubjectET);
				final EditText tet = mView.findViewById(R.id.sendTextET);

				receiver.setText(mailSend);
				ret.setText(mailSend);
				subject.setText(mailSubject);
				set.setText(mailSubject);
				text.setText(signature);
				tet.setText(signature);
				receiver.setVisibility(View.GONE);
				text.setVisibility(View.GONE);
				subject.setVisibility(View.GONE);
				ret.setVisibility(View.VISIBLE);
				set.setVisibility(View.VISIBLE);
				tet.setVisibility(View.VISIBLE);

				alertDialogBuilderUserInput
						.setCancelable(true)
						.setPositiveButton("Save", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialogBox, int id) {
								// ToDo get user input here
								mailSend=ret.getText().toString();
								mailSubject=set.getText().toString();
								signature=tet.getText().toString();
								editor.putString("subject",mailSubject).putString("receiver",mailSend).putString("signature",signature).commit();
							}
						})

						.setNegativeButton("Cancel",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialogBox, int id) {
										dialogBox.cancel();
									}
								});

				AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
				alertDialogAndroid.show();
			}
		});

	}
}
