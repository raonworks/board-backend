package com.raonworks.boardback.data.dto.object;

import com.raonworks.boardback.repository.resultSet.GetFavoriteListResultSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteItemList {
  private String email;
  private String nickname;
  private String profileImage;

  private FavoriteItemList(GetFavoriteListResultSet resultSet) {
    this.email = resultSet.getEmail();
    this.nickname = resultSet.getNickname();
    this.profileImage = resultSet.getProfileImage();
  }

  public static List<FavoriteItemList> copyList(List<GetFavoriteListResultSet> resultSets) {
    List<FavoriteItemList> list = new ArrayList<FavoriteItemList>();
    for (GetFavoriteListResultSet resultSet : resultSets) {
      FavoriteItemList favoriteItemList = new FavoriteItemList(resultSet);
      list.add(favoriteItemList);
    }
    return list;
  }

}
