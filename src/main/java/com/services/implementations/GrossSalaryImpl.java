package com.services.implementations;

import com.data.GrossSalary;
import org.springframework.stereotype.Service;


@Service
public class GrossSalaryImpl {

    public double convertGrossSalaryToNetSalary(GrossSalary grossSalary) {

      /*  source for tax rates of Belgium:
          https://financien.belgium.be/nl/particulieren/belastingaangifte/tarieven-belastbaar-inkomen/tarieven#q1

          https://businessam.be/hoeveel-hou-je-netto-over-als-je-een-loonsverhoging-krijgt/    */


        double net = 0;

        double rest = 0;


        if (grossSalary.getSalary() < 1128.33) {

            net = grossSalary.getSalary() * 0.75;
        } else if (grossSalary.getSalary() < 1991.66) {


            net = 1128.33 * 0.75;

            rest = grossSalary.getSalary() - 1128.33;

            net += rest * 0.60;

        } else if (grossSalary.getSalary() < 3446.66) {


            net = 1128.33 * 0.75;
            net += (1991.66 - 1128.33) * 0.6;

            rest = grossSalary.getSalary() - 1991.66;

            net += rest * 0.55;

        } else {


            net = 1128.33 * 0.75;
            net += (1991.66 - 1128.33) * 0.6;
            net += (3446.66 - 1991.66) * 0.55;


            rest = grossSalary.getSalary() - 3446.66;

            net += rest * 0.5;

        }


        switch (grossSalary.getNumberOfChild()) {

            case 1:
                net += 39;
                break;
            case 2:
                net += 110;
                break;
            case 3:
                net += 291;
                break;
            case 4:
                net += 510;
                break;

            case 5:
                net += 750;
                break;

            case 6:
                net += 991;
                break;
            case 7:
                net += 1232;
                break;

        }

        if (grossSalary.getNumberOfChild() >= 8) {

            net += 1498;
            net += (grossSalary.getNumberOfChild() - 8) * 268;

        }


        return net;
    }


}
