package ro.trellteam.core.comparator;

import ro.trellteam.core.domain.CardLog;

import java.util.Comparator;

public class LogComparator implements Comparator<CardLog> {
    @Override
    public int compare(CardLog l1, CardLog l2) {
        return l2.getLogDate().compareTo(l1.getLogDate());
    }
}