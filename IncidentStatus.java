public enum IncidentStatus {
    CRASH, SAFE;

    public IncidentStatus next()
    {
        return values()[(ordinal()+1)%2];
    }
}
