package com.bettersoft.nextgen4api.rest;

import com.bettersoft.nextgen4api.config.Const;
import com.bettersoft.nextgen4api.facade.IAuthenticationFacade;
import com.bettersoft.nextgen4api.model.Rota;
import com.bettersoft.nextgen4api.search.model.impl.RotaSearch;
import com.bettersoft.nextgen4api.repository.RotasRepository;
import com.bettersoft.nextgen4api.search.repository.impl.RotaSearchRepository;
import com.bettersoft.nextgen4api.rest.generic.GenericController;
import com.bettersoft.nextgen4api.search.filter.RotaFilter;
import com.bettersoft.nextgen4api.rest.payload.request.RotaRequest;
import com.bettersoft.nextgen4api.rest.payload.response.RotaResponse;
import com.bettersoft.nextgen4api.rest.payload.response.generic.GenericResponse;
import com.bettersoft.nextgen4api.service.RotaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;


@SecurityRequirement(name = "nextgenapi")
@CrossOrigin(origins = "*", maxAge = Const.MAX_AGE)
@RestController
@RequestMapping("/api/rotas")
public class RotaController extends GenericController<RotaSearch, Rota, RotaRequest, RotaResponse> {

    private final IAuthenticationFacade authenticationFacade;

    public RotaController(
            RotasRepository repository,
            RotaSearchRepository searchRepository,
            IAuthenticationFacade authenticationFacade,
            ModelMapper modelMapper,
            RotaService service
    ) {
        super(
                repository,
                searchRepository,
                modelMapper,
                authenticationFacade,
                service);
        this.authenticationFacade = authenticationFacade;
    }

    @GetMapping("/my-routes")
    @PreAuthorize(Const.GRANT_FOR_ALL)
    public  ResponseEntity<List<Rota>> myRotas() {
        var user = authenticationFacade.getAuthentication().getPrincipal();
        return ResponseEntity.ok(List.of());
    }

    @PreAuthorize(Const.GRANT_ADMIN)
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public ResponseEntity<Page<GenericResponse>> getPage(RotaFilter filter, Pageable pageable){
        return super.getPageBySearch(Objects.nonNull(filter) ? filter.getFilter() : "", pageable);
    }

    @Override
    @PreAuthorize(Const.GRANT_ADMIN)
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{uuid}")
    public ResponseEntity<GenericResponse<Rota, RotaResponse>> getOne(@PathVariable String uuid){
        return super.getOne(uuid);
    }

    @Override
    @PreAuthorize(Const.GRANT_ADMIN)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{uuid}")
    public ResponseEntity<RotaResponse> update(@PathVariable String uuid, @RequestBody RotaRequest updated){
        return super.update(uuid, updated);
    }

    @Override
    @PreAuthorize(Const.GRANT_ADMIN)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public ResponseEntity<RotaResponse> create(@RequestBody RotaRequest request){
        return super.create(request);
    }

    @PreAuthorize(Const.GRANT_ADMIN)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{uuid}")
    public ResponseEntity<RotaResponse> delete(@PathVariable String uuid){
       return super.delete(uuid, RotaRequest.class);
    }

}
