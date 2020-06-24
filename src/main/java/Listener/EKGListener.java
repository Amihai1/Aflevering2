/** @author Amihai */
package Listener;

import DTO.EKGDTO;

import java.util.LinkedList;

public interface EKGListener {
    void notify(LinkedList<EKGDTO> data);
}
