/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dbourreau.sample.test.presenter;

import android.content.Context;

import com.dbourreau.sample.domain.interactor.GetUserDetails;
import com.dbourreau.sample.domain.interactor.GetUserDetails.Params;
import com.dbourreau.sample.presentation.mapper.UserModelDataMapper;
import com.dbourreau.sample.presentation.presenter.UserDetailsPresenter;
import com.dbourreau.sample.presentation.view.UserDetailsView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.observers.DisposableObserver;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsPresenterTest {

    private static final int USER_ID = 1;

    private UserDetailsPresenter userDetailsPresenter;

    @Mock
    private Context mockContext;
    @Mock
    private UserDetailsView mockUserDetailsView;
    @Mock
    private GetUserDetails mockGetUserDetails;
    @Mock
    private UserModelDataMapper mockUserModelDataMapper;

    @Before
    public void setUp() {
        userDetailsPresenter = new UserDetailsPresenter(mockGetUserDetails, mockUserModelDataMapper);
        userDetailsPresenter.setView(mockUserDetailsView);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testUserDetailsPresenterInitialize() {
        given(mockUserDetailsView.context()).willReturn(mockContext);

        userDetailsPresenter.initialize(USER_ID);

        verify(mockUserDetailsView).hideRetry();
        verify(mockUserDetailsView).showLoading();
        verify(mockGetUserDetails).execute(any(DisposableObserver.class), any(Params.class));
    }
}
