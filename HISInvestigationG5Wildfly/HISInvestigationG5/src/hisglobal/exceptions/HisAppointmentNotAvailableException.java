package hisglobal.exceptions;

public class HisAppointmentNotAvailableException extends HisException {

	public HisAppointmentNotAvailableException()
	{
		super("Appointment Not Available");
	}

	public HisAppointmentNotAvailableException(String _msg)
	{
		super(_msg);
	}
}
