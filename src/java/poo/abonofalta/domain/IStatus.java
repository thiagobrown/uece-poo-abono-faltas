/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.abonofalta.domain;

/**
 *
 * @author thiago
 */
public interface IStatus {
    
    public void solicitar(Solicitacao solicitacao);
    
    public void aprovar(Solicitacao solicitacao);
    
    public void recusar(Solicitacao solicitacao);
    
    public void retornar(Solicitacao solicitacao);
    
    public void cancelar(Solicitacao solicitacao);
    
    public void retornarAprovacao(Solicitacao solicitacao);
}
