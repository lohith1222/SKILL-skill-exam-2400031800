package com.klef.soa.task.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.soa.task.entity.Newspaper;
import com.klef.soa.task.repository.NewspaperRepository;
import com.klef.soa.task.service.NewspaperService;

@Service
public class NewspaperServiceImpl implements NewspaperService
{
    @Autowired
    private NewspaperRepository repo;

    @Override
    public Newspaper insert(Newspaper newspaper)
    {
        return repo.save(newspaper);
    }

    @Override
    public Newspaper update(Long id, Newspaper newspaper)
    {
        Optional<Newspaper> optional = repo.findById(id);

        if(optional.isPresent())
        {
            Newspaper n = optional.get();

            n.setName(newspaper.getName());
            n.setType(newspaper.getType());
            n.setStatus(newspaper.getStatus());
            n.setLocation(newspaper.getLocation());

            return repo.save(n);
        }
        else
        {
            return null;
        }
    }

    @Override
    public void delete(Long id)
    {
        repo.deleteById(id);
    }

    @Override
    public List<Newspaper> getAll()
    {
        return repo.findAll();
    }

    @Override
    public Newspaper getById(Long id)
    {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Newspaper> findByName(String name)
    {
        return repo.findByName(name);
    }

    @Override
    public List<Newspaper> findByType(String type)
    {
        return repo.findByType(type);
        
    }

}