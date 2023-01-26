package pims.integrator.service;

import pims.integrator.dto.FingerDto;

public interface ABISService {
    String verifyFinger(FingerDto fingerDto);
}
