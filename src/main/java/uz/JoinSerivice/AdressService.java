package uz.JoinSerivice;

import uz.Model.Adress;
import uz.result.Result;

public interface AdressService {
    Result SaveUser(String userId, String region);

    Adress getAdress(String userId);
}
