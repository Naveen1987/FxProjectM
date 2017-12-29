/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fynisys.request.service;

/**
 *
 * @author daffodil-11
 */
public interface RequestResponseInterface {
        /**
     *
     */
    public void failureResponse(String errorMsg);
    public void successResponse(String response);
    
}
