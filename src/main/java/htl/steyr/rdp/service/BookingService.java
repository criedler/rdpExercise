package htl.steyr.rdp.service;

import htl.steyr.rdp.dto.BookingApartmentDto;
import htl.steyr.rdp.dto.BookingDto;
import htl.steyr.rdp.dto.BookingSupplementaryPackageDto;
import htl.steyr.rdp.model.*;
import htl.steyr.rdp.repository.ApartmentRepository;
import htl.steyr.rdp.repository.BookingRepository;
import htl.steyr.rdp.repository.CustomerRepository;
import htl.steyr.rdp.repository.SupplementaryPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private SupplementaryPackageRepository supplementaryPackageRepository;


    @Autowired
    private BookingRepository repo;

    public Booking map(BookingDto in) {
        var b = new Booking();
        var customer = customerRepository.findById(in.customerId());
        customer.ifPresent(b::setCustomer);

        List<BookingToApartment> bta = new ArrayList<>();
        for (var a : in.apartments()) {
            var optional = apartmentRepository.findById(a.apartmentId());
            if (optional.isPresent()) {
                var tmp = new BookingToApartment();
                tmp.setBooking(b);
                try {
                    java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(a.dateFrom());
                    tmp.setStartDate(new Date(utilDate.getTime()));
                    utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(a.dateTo());
                    tmp.setEndDate(new Date(utilDate.getTime()));
                } catch(Exception e) {
                    e.printStackTrace();
                }
                tmp.setApartment(optional.get());
                bta.add(tmp);
            }
        }

        b.setApartments(bta);

        List<BookingToSupplementaryPackage> btsp = new ArrayList<>();

        for (var s : in.packages()) {
            var optional = supplementaryPackageRepository.findById(s.packageId());
            if (optional.isPresent()) {
                var tmp = new BookingToSupplementaryPackage();
                tmp.setBooking(b);

                try {
                    java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(s.dateFrom());
                    tmp.setStartDate(new Date(utilDate.getTime()));
                    utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(s.dateTo());
                    tmp.setEndDate(new Date(utilDate.getTime()));
                } catch(Exception e) {
                    e.printStackTrace();
                }

                tmp.setAmount(s.amount());
                tmp.setSupplementaryPackage(optional.get());
                btsp.add(tmp);
            }
        }

        b.setPackages(btsp);

        return b;
    }

    public void save(BookingDto booking) {
        Booking b = map(booking);
        repo.save(b);
    }
}
