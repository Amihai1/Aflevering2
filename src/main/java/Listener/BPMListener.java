package Listener;

import DTO.BPMDTO;


public interface BPMListener {
    void notify(BPMDTO data);
}
